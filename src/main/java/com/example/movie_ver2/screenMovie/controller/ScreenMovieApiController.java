package com.example.movie_ver2.screenMovie.controller;

import com.example.movie_ver2.screenMovie.dto.RequestScreenMovieDto;
import com.example.movie_ver2.screenMovie.dto.ScreenMovieDto;
import com.example.movie_ver2.screenMovie.service.ScreenMovieService;
import com.example.movie_ver2.theater.dto.ResultJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/screenMovie")
public class ScreenMovieApiController {

    private final ScreenMovieService screenMovieService;

    @GetMapping("/getAllScreenMovies")
    public ResponseEntity<ResultJson<?>> getAllScreenMovies() {
        try{
            List<ScreenMovieDto> screenMovieDtos = screenMovieService.getALl();
            if(screenMovieDtos.isEmpty()){
                return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", "현재 상영 중인 영화가 없습니다."));
            }
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", screenMovieDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @GetMapping("/getScreenMovies/{theaterId}")
    public ResponseEntity<ResultJson<?>> getScreenMovies(@PathVariable Long theaterId) {
        try{
            List<ScreenMovieDto> screenMovieDtos = screenMovieService.getScreenMoviesByTheater(theaterId);
            if(screenMovieDtos.isEmpty()){
                return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", "해당 영화관은 현재 상영 중인 영화가 없습니다."));
            }
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", screenMovieDtos));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @PostMapping("/updateScreenMovie/{theaterId}")
    public ResponseEntity<ResultJson<?>> updateScreenMovie(@PathVariable Long theaterId, @Valid @RequestBody RequestScreenMovieDto requestDto) {
        try{
            screenMovieService.add(theaterId, requestDto);
            return ResponseEntity.ok(new ResultJson<>(201, "추가 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "추가 실패", e.getMessage()));
        }
    }

    @DeleteMapping("/deleteScreenMovie/{theaterId}")
    public ResponseEntity<ResultJson<?>> deleteScreenMovie(@PathVariable Long theaterId, @Valid @RequestBody RequestScreenMovieDto requestDto) {
        try{
            screenMovieService.remove(theaterId, requestDto);
            return ResponseEntity.ok(new ResultJson<>(200, "삭제 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "삭제 실패", e.getMessage()));
        }
    }
}
