package com.example.movie_ver2.screenMovie.repository;

import com.example.movie_ver2.screenMovie.entity.ScreenMovies;
import com.example.movie_ver2.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenMovieRepository extends JpaRepository<ScreenMovies, Long> {
    List<ScreenMovies> findByTheater(Theater theater);
}
