package com.example.movie_ver2.schedule.controller;


import com.example.movie_ver2.core.apiResponse.ApiResponse;
import com.example.movie_ver2.schedule.dto.CreateScheduleRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleApiController {


    @PostMapping("/api/schedule/createSchedule")
    public ResponseEntity<ApiResponse<?>> createSchedule(CreateScheduleRequestDto requestDto){

    }
}
