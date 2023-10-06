package com.example.movie_ver2.schedule.controller;


import com.example.movie_ver2.core.apiResponse.ApiResponse;
import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.member.exception.DuplicateEmailException;
import com.example.movie_ver2.schedule.dto.CreateScheduleRequestDto;
import com.example.movie_ver2.schedule.entity.Schedule;
import com.example.movie_ver2.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RequiredArgsConstructor
@RestController
public class ScheduleApiController {

    private final ScheduleService scheduleService;

    @PostMapping("/api/schedule/createSchedule")
    public ResponseEntity<ApiResponse<?>> createSchedule(CreateScheduleRequestDto requestDto){

        try {
            Schedule savedSchedule = scheduleService.saveSchedule(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "스케줄 등록 성공", savedSchedule));
        } catch (IllegalStateException E) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "스케줄 등록 실패", null));
        }
    }

//    @GetMapping("/api/schedule/findScheduleByScreen/{screenroomId}")
//    public ResponseEntity<ApiResponse<?>> findScheduleByScreenroomId(@PathVariable Long screenroomId){
//
//        try {
//            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "상영일정 검색 성공", Map.of("schedule", savedSchedule)));
//        }catch (IllegalStateException E){
//            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "상영일정 검색 성공", null));
//        }
//    }



}
