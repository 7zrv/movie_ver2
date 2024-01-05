package com.example.movie_ver2.movie.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class GetMovieRequestDto {

    private int pageNum;
    private int pageSize;
    private String criteria;

    public GetMovieRequestDto(){
        this.pageSize = 5;
        this.criteria = "openingDate";
    }
}
