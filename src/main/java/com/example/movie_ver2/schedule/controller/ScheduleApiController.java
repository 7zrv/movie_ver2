package com.example.movie_ver2.schedule.controller;


import com.example.movie_ver2.core.apiResponse.ApiResponse;

import com.example.movie_ver2.schedule.dto.*;
import com.example.movie_ver2.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ScheduleApiController {

    private final ScheduleService scheduleService;

    @PostMapping("/api/schedule/createSchedule")
    public ResponseEntity<ApiResponse<?>> createSchedule(@RequestBody CreateScheduleRequestDto requestDto){

        try {
            scheduleService.saveSchedule(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "스케줄 등록 성공", null));
        } catch (IllegalStateException E) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "스케줄 등록 실패", null));
        }

    }

    @GetMapping("/api/schedule/findScheduleByHall")
    public ResponseEntity<ApiResponse<?>> searchSchedules(SearchScheduleRequestDto requestDto){

        try {
            List<SearchSchedulesResponseDto> scheduleInfo = scheduleService.searchSchedules(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "상영일정 검색 성공", scheduleInfo));
        }catch (IllegalStateException E){
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "상영일정 검색 실패", null));
        }

    }

    @GetMapping("/api/schedule/findScheduleByHall/{scheduleId}")
    public ResponseEntity<ApiResponse<?>> searchSchedule(@PathVariable Long scheduleId){

        try {
            SearchScheduleResponseDto scheduleInfo = scheduleService.searchSchedule(scheduleId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "상영일정 검색 성공", scheduleInfo));
        }catch (IllegalStateException E){
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "상영일정 검색 실패", null));
        }

    }

    @PatchMapping("/api/schedule/modifyScheduleInfo")
    public ResponseEntity<ApiResponse<?>> modifyScheduleInfo(@RequestBody ModifyScheduleRequestDto requestDto){

        try {
            scheduleService.modifySchedule(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "상영시간 수정 성공", null));
        }catch (IllegalArgumentException E){
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "상영시간 수정 실패", null));
        }

    }

    @DeleteMapping("/api/schedule/deleteSchedule/{scheduleId}")
    public ResponseEntity<ApiResponse<?>> deleteSchedule(@PathVariable Long scheduleId){

        try {
            scheduleService.deleteSchedule(scheduleId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "상영일정 삭제 성공", null));
        }catch (IllegalArgumentException E){
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "상영일정 삭제 실패", null));
        }

    }




}
