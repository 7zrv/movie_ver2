package com.example.movie_ver2.review.repository;

import com.example.movie_ver2.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovie(Long movieId);
    List<Review> findByMember(Long memberId);
    Review findByMovieAndMember(Long movieId, Long memberId);
}
