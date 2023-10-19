package com.example.movie_ver2.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TheaterController {

    @GetMapping("/create/theater")
    public String createTheater() {
        return "theaterHtml/create-theater";
    }
    @GetMapping("/theater")
    public String theaterList() {
        return "theaterHtml/theater";
    }
    @GetMapping("/modify/theater/{theaterId}")
    public String modifyTheater() {
        return "theaterHtml/TheaterEdit";
    }
}
