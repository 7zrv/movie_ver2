package com.example.movie_ver2.review.service;

import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.member.repository.MemberRepository;
import com.example.movie_ver2.review.dto.MyReviewDto;
import com.example.movie_ver2.review.dto.RequestReviewDto;
import com.example.movie_ver2.review.entity.Review;
import com.example.movie_ver2.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Review saveReview(Long memberId, Long movieId, RequestReviewDto requestDto) {
        Member member = memberRepository.getReferenceById(memberId);
                                                //          EntityNotFoundException("해당 멤버는 존재하지 않습니다."));
        Long movie = movieId;
        return reviewRepository.save(requestDto.toEntity(member, movie));
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


    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));
    }


    public List<Review> getReviewsByMovie(Long movieId) {
        //movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("해당 영화는 존재하지 않습니다."));
        List<Review> reviews= reviewRepository.findByMovie(movieId);
        return reviews != null ? reviews : Collections.emptyList();
    }


    public List<Review> getReviewsByMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 존재하지 않습니다."));
        List<Review> reviews= reviewRepository.findByMember(member);
        return reviews != null ? reviews : Collections.emptyList();
    }

    public Review getReviewByMovieAndMember(Long movieId, Long memberId) {
        //memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 존재하지 않습니다."));
        //movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("해당 영화는 존재하지 않습니다."));
        Member member = memberRepository.getReferenceById(memberId);
        return reviewRepository.findByMovieAndMember(movieId, member);
    }
}
