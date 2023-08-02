package com.example.movie_ver2.member.exception;


import lombok.Getter;
import lombok.Setter;


public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
