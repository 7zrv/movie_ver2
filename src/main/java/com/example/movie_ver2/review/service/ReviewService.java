package com.example.movie_ver2.review.service;

import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.member.repository.MemberRepository;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.repository.MovieRepository;
import com.example.movie_ver2.review.dto.MovieRatingDto;
import com.example.movie_ver2.review.dto.MovieReviewDto;
import com.example.movie_ver2.review.dto.MyReviewDto;
import com.example.movie_ver2.review.dto.RequestReviewDto;
import com.example.movie_ver2.review.entity.Review;
import com.example.movie_ver2.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public Review saveReview(Long memberId, Long movieId, RequestReviewDto requestDto) {
        try{
            Member member = memberRepository.getReferenceById(memberId);
            //예외처리 EntityNotFoundException("해당 멤버는 존재하지 않습니다."));
            Movie movie = movieRepository.getReferenceById(movieId);
            return reviewRepository.save(requestDto.toEntity(member, movie));
        }catch (Exception e){
            throw new EntityNotFoundException("해당 는 존재하지 않습니다.");
        }
    }

    @Transactional
    public void updateReview(Long id, RequestReviewDto requestDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));
        review.update(requestDto);
    }

    @Transactional
    public void delete(Long id) {
        try{
            reviewRepository.deleteById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("해당 리뷰는 존재하지 않습니다.");
        }
    }

    public boolean checkExist(Long memberId, Long movieId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 존재하지 않습니다."));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("해당 영화는 존재하지 않습니다."));
        return reviewRepository.existsByMovieAndMember(movie, member);
    }

    public MovieRatingDto getMovieRating(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("해당 영화는 존재하지 않습니다."));
        return MovieRatingDto.of(movie);

    }


    public MyReviewDto getReviewById(Long id) {
        return MyReviewDto.of(reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다.")));
    }


    public List<MovieReviewDto> getReviewsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("해당 영화는 존재하지 않습니다."));
        return reviewRepository.findByMovie(movie).stream()
                .map(MovieReviewDto::of)
                .collect(Collectors.toList());
    }

    public Page<MovieReviewDto> getReviewsWithPageByMovie(Long movieId, Pageable pageable) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("해당 영화는 존재하지 않습니다."));
        return reviewRepository.findByMovie(movie, pageable)
                .map(MovieReviewDto::of);
    }

    public List<MyReviewDto> getReviewsByMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 존재하지 않습니다."));
        return reviewRepository.findByMember(member).stream()
                .map(MyReviewDto::of)
                .collect(Collectors.toList());
    }

    public Review getReviewByMovieAndMember(Long movieId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 존재하지 않습니다."));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("해당 영화는 존재하지 않습니다."));
        return reviewRepository.findByMovieAndMember(movie, member);
    }
}
