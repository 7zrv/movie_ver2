package com.example.movie_ver2.screenMovie.dto;

import com.example.movie_ver2.movie.entity.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

@Getter
@NoArgsConstructor
public class ScreenMovieDto {
    private Long id;
    private String title;
    private Integer runtime;
    private LocalDate openingDate;

    @Builder
    public ScreenMovieDto(Long id, String title, Integer runtime, LocalDate openingDate) {
        this.id = id;
        this.title = title;
        this.runtime = runtime;
        this.openingDate = openingDate;
    }

    //public int getTitle()

    public static ScreenMovieDto of(Movie movie) {
        return ScreenMovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .runtime(movie.getRuntime())
                .openingDate(movie.getOpeningDate())
                .build();
    }
}
