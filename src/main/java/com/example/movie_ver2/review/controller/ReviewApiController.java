package com.example.movie_ver2.review.controller;

import com.example.movie_ver2.review.dto.*;
import com.example.movie_ver2.review.entity.Review;
import com.example.movie_ver2.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/getMyReview/{memberId}")
    public ResponseEntity<ResultJson<?>> getMyReview(@PathVariable Long memberId) {
        try{
            List<MyReviewDto> reviewsDto = reviewService.getReviewsByMember(memberId);
            if(reviewsDto.isEmpty()){
                return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", "해당 멤버에 등록된 리뷰가 아직 존재하지 않습니다."));
            }
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", reviewsDto));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @GetMapping("/getMovieReview/{movieId}")
    public ResponseEntity<ResultJson<?>> getMovieReview(@PathVariable Long movieId) {
        try{
            List<MovieReviewDto> reviewsDto = reviewService.getReviewsByMovie(movieId);
            if(reviewsDto.isEmpty()){
                return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", "해당 영화에 등록된 리뷰가 아직 존재하지 않습니다."));
            }
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", reviewsDto));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @GetMapping("/getMovieReviewWithPage/{movieId}")
    public ResponseEntity<ResultJson<?>> getMovieReviewWithPage(@PathVariable Long movieId, @PageableDefault(size = 10) Pageable pageable) {
        try{
            Page<MovieReviewDto> reviewsDto = reviewService.getReviewsWithPageByMovie(movieId, pageable);
            if(reviewsDto.isEmpty()){
                return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", "해당 영화에 등록된 리뷰가 아직 존재하지 않습니다."));
            }
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", reviewsDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @GetMapping("/createReview/{memberId}/{movieId}")
    public ResponseEntity<ResultJson<?>> getCreateReview(@PathVariable Long memberId, @PathVariable Long movieId) {     //movieDto
        try{
            if(reviewService.checkExist(memberId, movieId)){
                throw new IllegalArgumentException("해당 영화에 대한 회원님의 리뷰가 이미 존재합니다.");
            }
            MovieRatingDto movieDto = reviewService.getMovieRating(movieId);
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", movieDto));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }

    @GetMapping("/modifyReview/{memberId}/{reviewId}")
    public ResponseEntity<ResultJson<?>> getModifyReview(@PathVariable Long memberId, @PathVariable Long reviewId) {
        try{
            MyReviewDto reviewDto = reviewService.getReviewById(reviewId);
            return ResponseEntity.ok(new ResultJson<>(200, "조회 성공", reviewDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "조회 실패", e.getMessage()));
        }
    }


    @PostMapping("/createReview/{memberId}/{movieId}")
    public ResponseEntity<ResultJson<?>> createReview(@PathVariable Long memberId, @PathVariable Long movieId, @Valid @RequestBody RequestReviewDto reviewDto) {
        try{
            Review review = reviewService.saveReview(memberId, movieId, reviewDto);
            URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(review.getId())
                    .toUri();
            return ResponseEntity.created(loc)
                    .body(new ResultJson<>(201, "등록 성공", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "등록 실패", e.getMessage()));
        }
    }

    //현재 멤버id와 수정할 리뷰의 멤버id 동일한지 확인
    @PatchMapping("/modifyReview/{memberId}/{reviewId}")
    public ResponseEntity<ResultJson<String>> modifyReview(@PathVariable Long memberId, @PathVariable Long reviewId, @Valid @RequestBody RequestReviewDto reviewDto) {
        try{
            reviewService.updateReview(reviewId, reviewDto);
            return ResponseEntity.ok(new ResultJson<>(201, "수정 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "수정 실패", e.getMessage()));
        }
    }

    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<ResultJson<String>> deleteReview(@PathVariable Long reviewId) {
        try{
            reviewService.delete(reviewId);
            return ResponseEntity.ok(new ResultJson<>(200, "삭제 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResultJson<>(404, "삭제 실패", e.getMessage()));
        }
    }

}
