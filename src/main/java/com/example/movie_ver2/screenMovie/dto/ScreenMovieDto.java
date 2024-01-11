package com.example.movie_ver2.screenMovie.dto;

import com.example.movie_ver2.movie.entity.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ScreenMovieDto {
    private Long id;
    private String title;
    private LocalDate openingDate;

    @Builder
    public ScreenMovieDto(Long id, String title, LocalDate openingDate) {
        this.id = id;
        this.title = title;
        this.openingDate = openingDate;
    }

    //public int getTitle()

    public static ScreenMovieDto of(Movie movie) {
        return ScreenMovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .openingDate(movie.getOpeningDate())
                .build();
    }
}
