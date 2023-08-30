package com.example.movie_ver2.review.dto;

import com.example.movie_ver2.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyReviewDto {
    private Long id;
    private String title;
    private Integer rating;
    private String content;
    private LocalDateTime mod_date;

    @Builder
    public MyReviewDto(Long id, String title, Integer rating, String content, LocalDateTime mod_date) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.mod_date = mod_date;
    }

    public static MyReviewDto of(Review review) {
        return MyReviewDto.builder()
                .id(review.getId())
                .title(review.getMovie().getTitle())
                .rating(review.getRating())
                .content(review.getContent())
                .mod_date(review.getMod_date())
                .build();
    }
}
