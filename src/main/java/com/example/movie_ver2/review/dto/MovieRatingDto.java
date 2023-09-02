package com.example.movie_ver2.review.dto;

import com.example.movie_ver2.movie.entity.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MovieRatingDto {
    private String title;
    private double average;
    private int totalRatings;

    @Builder
    public MovieRatingDto(String title, double sumOfRating, int totalRatings) {
        this.title = title;
        this.average = sumOfRating/totalRatings;
        this.totalRatings = totalRatings;
    }

    public static MovieRatingDto of(Movie movie) {
        return MovieRatingDto.builder()
                .title(movie.getTitle())
                .sumOfRating(movie.getSumOfRating())
                .totalRatings(movie.getTotalRatings())
                .build();
    }
}