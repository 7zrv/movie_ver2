package com.example.movie_ver2.theater.controller;

import com.example.movie_ver2.theater.dto.ResultJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice(basePackageClasses = TheaterApiController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResultJson<?>> constraintViolationException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResultJson<>(400, "조회 실패", e.getMessage()));
    }
}
