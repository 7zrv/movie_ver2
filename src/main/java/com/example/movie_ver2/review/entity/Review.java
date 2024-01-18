package com.example.movie_ver2.review.entity;

import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.review.dto.RequestReviewDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "reviews")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id", updatable = false, nullable = false)
    private Member member;     //manytoone Member, joincolumn    Member class에서는 onetomany(mappedby="member")List<Review>

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "movie_id", updatable = false, nullable = false)
    private Movie movie;      //manytoone Movie, joincolumn

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "content", length = 300, nullable = false)
    private String content;

    @Column(name="reg_date", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime reg_date;     //CreatedDate 사용 추천 따로 클래서 만들어 생성

    @Column(name="mod_date")
    @UpdateTimestamp
    private LocalDateTime mod_date;

    @Builder
    public Review(Member member, Movie movie, Integer rating, String content) {
        this.member = member;
        this.movie = movie;
        this.rating = rating;
        this.content = content;
    }

    public void update(RequestReviewDto requestDto) {
        if(requestDto.getRating() != null) {
            this.rating = requestDto.getRating();
        }
        if(StringUtils.hasText(requestDto.getContent())){
            this.content = requestDto.getContent();
        }
    }

}
