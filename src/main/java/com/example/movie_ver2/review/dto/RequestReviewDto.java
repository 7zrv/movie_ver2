package com.example.movie_ver2.review.dto;

import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class RequestReviewDto {
    @Max(value = 10)
    @Positive
    private Integer rating;
    @Size(max = 500, min = 1)
    private String content;

    public Review toEntity(Member member, Movie movie) {           //Member, Movie
        return Review.builder()
                .member(member)
                .movie(movie)
                .rating(this.rating)
                .content(this.content)
                .build();
    }
}
