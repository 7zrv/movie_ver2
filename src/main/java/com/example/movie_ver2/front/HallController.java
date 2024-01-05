package com.example.movie_ver2.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HallController {
    @GetMapping("/halls/{theaterId}")
    public String hallListByTheater() {
        return "hallHtml/halls";
    }
    @GetMapping("/create/hall/{theaterId}")
    public String createHall() {
        return "hallHtml/create-hall";
    }
    @GetMapping("/modify/hall/{theaterId}/{hallId}")
    public String modifyHall() {
        return "hallHtml/hallEdit";
    }
}
