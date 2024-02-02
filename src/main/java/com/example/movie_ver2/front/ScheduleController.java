package com.example.movie_ver2.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    @GetMapping("/create/schedule")
    public String createSchedule() {
        return "scheduleHtml/create-schedule";
    }
}
