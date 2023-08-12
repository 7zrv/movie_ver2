package com.example.movie_ver2.movie.dto;

import com.example.movie_ver2.movie.entity.Movie;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieUploadRequestDto {

    private String title;
    private String director;
    private String cast;
    private String country;
    private String genre;
    private String runtime;
    private String age;
    private String content;
    private String posterImgPath;
    private List<String> previewImgPath;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private LocalDate openingDate;

    public Movie toEntity(){
        return Movie.builder()
                .title(this.title)
                .director(this.director)
                .cast(this.cast)
                .country(this.country)
                .genre(this.genre)
                .runtime(this.runtime)
                .age(this.age)
                .content(this.content)
                .posterImgPath(this.posterImgPath)
                .previewImgPath(this.previewImgPath)
                .openingDate(this.openingDate)
                .build();
    }

}
