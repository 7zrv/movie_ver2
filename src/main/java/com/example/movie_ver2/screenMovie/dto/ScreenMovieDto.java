package com.example.movie_ver2.screenMovie.dto;

import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.theater.entity.Theater;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScreenMovieDto {
    private Long id;
    private String title;

    @Builder
    public ScreenMovieDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static ScreenMovieDto of(Movie movie) {
        return ScreenMovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .build();
    }
}
