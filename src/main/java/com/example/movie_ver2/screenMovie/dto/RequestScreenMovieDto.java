package com.example.movie_ver2.screenMovie.dto;

import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.screenMovie.entity.ScreenMovies;
import com.example.movie_ver2.theater.entity.Theater;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestScreenMovieDto {
    @UniqueElements
    private List<Long> movieId;

    public static ScreenMovies toEntity(Movie movie, Theater theater) {
        return ScreenMovies.builder()
                .theater(theater)
                .movie(movie)
                .build();
    }
}
