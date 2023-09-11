package com.example.movie_ver2.theater.controller;

import com.example.movie_ver2.theater.dto.RequestTheaterDto;
import com.example.movie_ver2.theater.dto.ResultJson;
import com.example.movie_ver2.theater.dto.TheaterAreaDto;
import com.example.movie_ver2.theater.dto.TheaterInfoDto;
import com.example.movie_ver2.theater.entity.Theater;
import com.example.movie_ver2.theater.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/theater")
public class TheaterApiController {

    private final TheaterService theaterService;

    @GetMapping("/getAllTheater")
    public ResponseEntity<ResultJson<?>> getAllTheater() {
        try{
            List<TheaterAreaDto> theaterDtos = theaterService.getALl();
            if(theaterDtos.isEmpty()){
                return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", "현재 등록된 영화관이 존재하지 않습니다."));
            }
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", theaterDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @GetMapping("/getTheaterInfo/{theaterId}")
    public ResponseEntity<ResultJson<?>> getTheaterInfo(@PathVariable Long theaterId) {
        try{
            TheaterInfoDto theaterDto = theaterService.getTheaterInfoById(theaterId);
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", theaterDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }


    @GetMapping("/getLocalTheaters")
    public ResponseEntity<ResultJson<?>> getLocalTheaters() {
        try{
            List<TheaterAreaDto> theaterDtos = theaterService.getTheatersByLocal("강원");
            if(theaterDtos.isEmpty()){
                return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", "해당 지역에 등록된 영화관이 존재하지 않습니다."));
            }
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", theaterDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @PostMapping("/createTheater")
    public ResponseEntity<ResultJson<?>> createTheater(@RequestBody RequestTheaterDto theaterDto) {
        try{
            if(theaterService.checkDuplicate(theaterDto.getArea())){
                throw new IllegalArgumentException("동일한 지점의 영화관이 이미 존재합니다.\n다시 입력해주세요.");
            }
            Theater theater = theaterService.saveTheater(theaterDto);
            return ResponseEntity.ok(new ResultJson<>(201, "등록 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "등록 실패", e.getMessage()));
        }
    }

    @PatchMapping("/modifyTheater/{theaterId}")
    public ResponseEntity<ResultJson<?>> modifyTheater(@PathVariable Long theaterId, @RequestBody RequestTheaterDto theaterDto) {
        try{
            if(theaterService.checkDuplicate(theaterDto.getArea())){
                throw new IllegalArgumentException("동일한 지점의 영화관이 이미 존재합니다.\n다시 입력해주세요.");
            }
            theaterService.updateTheater(theaterId, theaterDto);
            return ResponseEntity.ok(new ResultJson<>(201, "수정 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "수정 실패", e.getMessage()));
        }
    }

    @DeleteMapping("/deleteTheater/{theaterId}")
    public ResponseEntity<ResultJson<?>> deleteTheater(@PathVariable Long theaterId) {
        try{
            theaterService.delete(theaterId);
            return ResponseEntity.ok(new ResultJson<>(200, "삭제 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "삭제 실패", e.getMessage()));
        }
    }
}
