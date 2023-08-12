package com.example.movie_ver2.movie.controller;


import com.example.movie_ver2.movie.dto.ApiResponse;
import com.example.movie_ver2.movie.dto.MovieUploadRequestDto;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class MovieApiController {

    private final MovieService movieService;

    @PostMapping("/api/movie/uploadMovieInfo")
    public ResponseEntity<ApiResponse<?>> uploadMovieInfo(@RequestPart (value = "requestDto") MovieUploadRequestDto requestDto,
                                                          @RequestPart (value= "posterImage", required = false) MultipartFile posterImg,
                                                          @RequestPart (value= "previewImages", required = false) List<MultipartFile> previewImgs) throws IOException {
        Movie movie = movieService.saveMovieInfo(requestDto, posterImg, previewImgs);

        return ResponseEntity.ok(new ApiResponse<>(1, "조회 성공", movie));
    }

}
