package com.example.movie_ver2.movie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MovieUpdateRequestDto {

    private String title;
    private String director;
    private String cast;
    private String country;
    private Set<String> genre;
    private Integer runtime;
    private String age;
    private String content;
    private String posterImgPath;
    private List<String> previewImgPath;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private LocalDate openingDate;
}
