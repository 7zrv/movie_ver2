package com.example.movie_ver2.seat.controller;


import com.example.movie_ver2.core.apiResponse.ApiResponse;
import com.example.movie_ver2.schedule.dto.ModifyScheduleRequestDto;
import com.example.movie_ver2.seat.dto.*;

import com.example.movie_ver2.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SeatApiController {

    private final SeatService seatService;

    @PostMapping("/api/seat/saveSeat")
    public ResponseEntity<ApiResponse<?>> saveSeat(@RequestBody SaveSeatRequestDto requestDto){
        try {
            seatService.saveSeats(requestDto);
            return ResponseEntity.ok(new ApiResponse<>(1, "좌석 정보 등록 성공", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(0, "예기치 않은 오류 발생", null));
        }
    }

    @GetMapping("/api/seat/findSeatByScheduleId/{scheduleId}")
    public ResponseEntity<ApiResponse<?>> showSeatState(@PathVariable Long scheduleId){

        try {
            List<ShowSeatsResponseDto> responseDto = seatService.showSeatList(scheduleId);
            return ResponseEntity.ok(new ApiResponse<>(1, "좌석 정보 등록 성공", responseDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(0, "예기치 않은 오류 발생", null));
        }

    }

    @PatchMapping("/api/seat/selectSeats")
    public ResponseEntity<ApiResponse<?>> selectSeats(@RequestBody SelectSeatRequestDto requestDto){

        try {
            seatService.selectSeats(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "좌석 선택 성공", null));
        }catch (IllegalArgumentException E){
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "좌석 선택 실패", null));
        }

    }

    @PatchMapping("/api/seat/soldSeats")
    public ResponseEntity<ApiResponse<?>> soldSeats(@RequestBody SoldSeatsRequestDto requestDto){

        try {
            seatService.soldSeats(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "좌석 판매 성공", null));
        }catch (IllegalArgumentException E){
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "좌석 판매 실패", null));
        }

    }

    @DeleteMapping("/api/seat/deleteSeats")
    public ResponseEntity<ApiResponse<?>> DeleteSeats(@RequestBody DeleteSeatRequestDto requestDto){

        try {
            seatService.deleteSeats(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "좌석 삭제 성공", null));
        }catch (IllegalArgumentException E){
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "좌석 삭제 실패", null));
        }

    }











}
