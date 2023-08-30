package com.example.movie_ver2.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultJson<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultJson(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
