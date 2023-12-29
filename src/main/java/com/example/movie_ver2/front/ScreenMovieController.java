package com.example.movie_ver2.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScreenMovieController {
    @GetMapping("/screenMovies/{theaterId}")
    public String screenMovieListByTheater() {
        return "screenHtml/screenMovie";
    }
}
