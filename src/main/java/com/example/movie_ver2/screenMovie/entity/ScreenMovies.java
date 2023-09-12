package com.example.movie_ver2.screenMovie.entity;

import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.theater.entity.Theater;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "screen_movies")
@Entity
public class ScreenMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="theater_id", nullable = false)//insertable = false, updatable = false
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "movie_id", nullable = false)
    private Movie movie;

    //더빙여부
    private Boolean sub;

    @Builder
    public ScreenMovies(Theater theater, Movie movie) {
        this.theater = theater;
        this.movie = movie;
    }
}
