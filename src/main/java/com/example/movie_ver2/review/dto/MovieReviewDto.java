package com.example.movie_ver2.review.dto;

import com.example.movie_ver2.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MovieReviewDto {
    private Long id;
    private Long member_id;
    private Integer rating;
    private String content;
    private LocalDateTime mod_date;

    @Builder
    public MovieReviewDto(Long id, Long member_id, Integer rating, String content, LocalDateTime mod_date) {
        this.id = id;
        this.member_id = member_id;
        this.rating = rating;
        this.content = content;
        this.mod_date = mod_date;
    }

    public static MovieReviewDto of(Review review) {
        return MovieReviewDto.builder()
                .id(review.getId())
                .member_id(review.getMember().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .mod_date(review.getMod_date())
                .build();
    }
}
