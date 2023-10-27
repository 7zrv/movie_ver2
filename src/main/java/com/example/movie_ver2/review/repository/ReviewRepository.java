package com.example.movie_ver2.review.repository;

import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovie(Movie movie);
    Page<Review> findByMovie(Movie movie, Pageable pageable);
    List<Review> findByMember(Member member);
    Review findByMovieAndMember(Movie movie, Member member);
    Boolean existsByMovieAndMember(Movie movie, Member member);

    //Page<Review> findAll(Pageable pageable);
}
