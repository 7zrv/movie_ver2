package com.example.movie_ver2.front;


import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class MovieController {

    private final MovieService movieService;

    @RequestMapping("/movie")
    public String showMoviePage(Model model){
        List<Movie> movies = movieService.getMovies(0, 5 , "openingDate");
        model.addAttribute("movies", movies);

        return "movieHtml/movie";
    }

    @GetMapping("/movieDetail/{movieId}")
    public String showMoviePage(@PathVariable Long movieId, Model model){

        Movie movie = movieService.getMovie(movieId);

        model.addAttribute("movie", movie);

        return "movieHtml/movieDetailPage";
    }


}
