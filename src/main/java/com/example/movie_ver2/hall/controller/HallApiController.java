package com.example.movie_ver2.hall.controller;

import com.example.movie_ver2.hall.dto.HallInfoDto;
import com.example.movie_ver2.hall.dto.RequestHallDto;
import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.hall.service.HallService;
import com.example.movie_ver2.theater.dto.ResultJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hall")
public class HallApiController {

    private final HallService hallService;

    @GetMapping("/getHallsByTheater/{theaterId}")
    public ResponseEntity<ResultJson<?>> getHallsByTheater(@PathVariable Long theaterId) {
        try{
            List<HallInfoDto> hallDto = hallService.getHallsByTheater(theaterId);
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", hallDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @GetMapping("/getHallInfo/{hallId}")
    public ResponseEntity<ResultJson<?>> getHallInfo(@PathVariable Long hallId) {
        try{
            HallInfoDto hallDto = hallService.getHallInfoById(hallId);
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", hallDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @PostMapping("/createHall/{theaterId}")
    public ResponseEntity<ResultJson<?>> createHall(@PathVariable Long theaterId, @Valid @RequestBody RequestHallDto hallDto) {
        try{
            if(hallService.checkDuplicate(theaterId, hallDto.getName())){
                throw new IllegalArgumentException("동일한 상영관명이 해당 영화관에 이미 존재합니다.\n다시 입력해주세요.");
            }
            Hall hall = hallService.saveHall(theaterId, hallDto);
            return ResponseEntity.ok(new ResultJson<>(201, "등록 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "등록 실패", e.getMessage()));
        }
    }

    @PatchMapping("/modifyHall/{theaterId}/{hallId}")
    public ResponseEntity<ResultJson<?>> modifyHall(@PathVariable Long theaterId, @PathVariable Long hallId, @Valid @RequestBody RequestHallDto hallDto) {
        try{
            if(hallService.checkDuplicateByIdNot(hallId, theaterId, hallDto.getName())){
                throw new IllegalArgumentException("동일한 상영관명이 해당 영화관에 이미 존재합니다.\n다시 입력해주세요.");
            }
            hallService.updateHall(hallId, hallDto);
            return ResponseEntity.ok(new ResultJson<>(201, "수정 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "수정 실패", e.getMessage()));
        }
    }

    @DeleteMapping("/deleteHall/{hallId}")
    public ResponseEntity<ResultJson<?>> deleteHall(@PathVariable Long hallId) {
        try{
            hallService.delete(hallId);
            return ResponseEntity.ok(new ResultJson<>(200, "삭제 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "삭제 실패", e.getMessage()));
        }
    }
}
