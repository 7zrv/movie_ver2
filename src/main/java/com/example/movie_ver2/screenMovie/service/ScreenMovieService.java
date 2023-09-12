package com.example.movie_ver2.screenMovie.service;

import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.repository.MovieRepository;
import com.example.movie_ver2.screenMovie.dto.RequestScreenMovieDto;
import com.example.movie_ver2.screenMovie.dto.ScreenMovieDto;
import com.example.movie_ver2.screenMovie.entity.ScreenMovies;
import com.example.movie_ver2.screenMovie.repository.ScreenMovieRepository;
import com.example.movie_ver2.theater.entity.Theater;
import com.example.movie_ver2.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreenMovieService {
    private final ScreenMovieRepository screenMovieRepository;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public void add(Long id, RequestScreenMovieDto requestDto) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화관은 존재하지 않습니다."));

        List<Movie> movies = movieRepository.findAllById(requestDto.getMovieId());
        List<ScreenMovies> screenMovies = movies.stream()
                .map((Movie movie) -> RequestScreenMovieDto.toEntity(movie, theater))
                .collect(Collectors.toList());
        screenMovieRepository.saveAll(screenMovies);
    }

    @Transactional
    public void remove(Long id, RequestScreenMovieDto requestDto) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화관은 존재하지 않습니다."));

        List<Long> movieId = requestDto.getMovieId();
        List<ScreenMovies> screenMovies = movieRepository.findAllById(movieId).stream()
                .map((Movie movie) -> RequestScreenMovieDto.toEntity(movie, theater))
                .collect(Collectors.toList());
        screenMovieRepository.deleteAll(screenMovies);
    }

    public List<ScreenMovieDto> getALl() {
        return screenMovieRepository.findAll().stream()
                .map(ScreenMovies::getMovie).distinct()
                .map(ScreenMovieDto::of)
                .collect(Collectors.toList());
    }

    public List<ScreenMovieDto> getScreenMoviesByTheater(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화관은 존재하지 않습니다."));
        return screenMovieRepository.findByTheater(theater).stream()
                .map(ScreenMovies::getMovie).distinct()
                .map(ScreenMovieDto::of)
                .collect(Collectors.toList());
    }
}
