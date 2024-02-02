package com.example.movie_ver2.movie.dto;

import com.example.movie_ver2.movie.entity.Movie;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Getter
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMovieResponseDto {

    private final Long id;
    private final String title;
    private final String director;
    private final String cast;
    private final String country;
    private final Set<String> genre;
    private final Integer runtime;
    private final String age;
    private final double sumOfRating;
    private final int totalRatings;
    private final String content;
    private final String posterImgPath;
    private final List<String> previewImgPath;
    private final LocalDate openingDate;

    @Builder
    public static GetMovieResponseDto from(Movie movie){

        return GetMovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .cast(movie.getCast())
                .country(movie.getCountry())
                .genre(movie.getGenre())
                .runtime(movie.getRuntime())
                .age(movie.getAge())
                .sumOfRating(movie.getSumOfRating())
                .totalRatings(movie.getTotalRatings())
                .content(movie.getContent())
                .posterImgPath(movie.getPosterImgPath())
                .previewImgPath(movie.getPreviewImgPath())
                .openingDate(movie.getOpeningDate())
                .build();
    }

}
