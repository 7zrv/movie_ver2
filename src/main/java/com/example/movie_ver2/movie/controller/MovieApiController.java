package com.example.movie_ver2.movie.controller;



import com.example.movie_ver2.core.apiResponse.ApiResponse;
import com.example.movie_ver2.movie.dto.GetMovieRequestDto;
import com.example.movie_ver2.movie.dto.GetMovieResponseDto;
import com.example.movie_ver2.movie.dto.MovieUpdateRequestDto;
import com.example.movie_ver2.movie.dto.MovieUploadRequestDto;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@RestController
public class MovieApiController {

    private final MovieService movieService;

    @PostMapping("/api/movie/uploadMovieInfo")
    public ResponseEntity<ApiResponse<?>> uploadMovieInfo(@RequestPart(value = "requestDto") MovieUploadRequestDto requestDto,
                                                          @RequestPart(value = "posterImage", required = false) MultipartFile posterImg,
                                                          @RequestPart(value = "previewImages", required = false) List<MultipartFile> previewImgs) {

        try {
            Movie movie = movieService.saveMovieInfo(requestDto, posterImg, previewImgs);
            return ResponseEntity.ok(new ApiResponse<>(1, "영화 정보 등록 성공", movie));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(0, "예기치 않은 오류 발생", null));
        }


    }

    @GetMapping("/api/movie/getAllMovieInfo")
    public ResponseEntity<ApiResponse<?>> getAllMovieInfo(GetMovieRequestDto requestDto){

        try {
            Page<GetMovieResponseDto> movies = movieService.getMovies(requestDto);
            return ResponseEntity.ok(new ApiResponse<>(1, "영화 목록 조회 성공", movies));
        }catch (RuntimeException e){
            return ResponseEntity.ok(new ApiResponse<>(0, "영화 정보 조회 실패", null));
        }

    }

    @GetMapping("/api/movie/getDetailMovieInfo/{movieId}")
    public ResponseEntity<ApiResponse<?>> getDetailMovieInfo(@PathVariable Long movieId){

        try {
            Movie movie = movieService.getMovie(movieId);
            return ResponseEntity.ok(new ApiResponse<>(1, "영화 상세정보 조회 성공", movie));
        }catch (NoSuchElementException e){
            return ResponseEntity.ok(new ApiResponse<>(0, "영화 상세정보 조회 실패", movieId));
        }

    }

    @GetMapping("/api/movie/getMovieInfoByGenre")
    public ResponseEntity<ApiResponse<?>> getMovieInfoByGenre(@RequestParam String genre){

        try {
            List<Movie> movies = movieService.getMoviesByGenre(genre);
            return ResponseEntity.ok(new ApiResponse<>(1, "영화 장르별 조회 성공", movies));
        } catch (NoSuchElementException e){
            return ResponseEntity.ok(new ApiResponse<>(0, "영화 조회 실패", genre));
        }

    }

    @PatchMapping("/api/movie/updateMovieInfo/{movieId}")
    public ResponseEntity<ApiResponse<?>> updateMovieInfo(@PathVariable Long movieId,
                                                          @RequestBody MovieUpdateRequestDto requestDto){

        try {
            Movie movie = movieService.updateMovieInfo(movieId, requestDto);
            return ResponseEntity.ok(new ApiResponse<>(1, "영화 업데이트 성공", movie));
        } catch (NoSuchElementException e){
            return ResponseEntity.ok(new ApiResponse<>(0, "영화 업데이트 실패", null));
        }

    }

    @DeleteMapping("/api/movie/deleteMovieInfo/{movieId}")
    public ResponseEntity<ApiResponse<?>> deleteMovieInfo(@PathVariable Long movieId){

        movieService.deleteMovie(movieId);

        return ResponseEntity.ok(new ApiResponse<>(1, "영화 삭제 성공", movieId));
    }


}
