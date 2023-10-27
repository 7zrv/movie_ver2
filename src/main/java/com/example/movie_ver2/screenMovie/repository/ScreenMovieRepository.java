package com.example.movie_ver2.screenMovie.repository;

import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.screenMovie.entity.ScreenMovies;
import com.example.movie_ver2.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenMovieRepository extends JpaRepository<ScreenMovies, Long> {
    //List<ScreenMovies> findByTheater(Theater theater);
    void deleteAllByMovieInAndTheater(List<Movie> movies, Theater theater);
    //이미 있는 상영영화는 무시하고 없는 영화만 추가
    //List<ScreenMovies> findAllByMovieInAndTheater(List<Movie> movies, Theater theater);
}
