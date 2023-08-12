package com.example.movie_ver2.movie.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "cast", nullable = false)
    private String cast;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "runtime", nullable = false)
    private String runtime;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "sum_of_rating")
    private double sumOfRating;

    @Column(name = "total_ratings", nullable = false)
    private int totalRatings;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "poster_img_path")
    private String posterImgPath;

    @ElementCollection
    @CollectionTable(name = "preview_images", joinColumns = @JoinColumn(name = "movie_id"))
    private List<String> previewImgPath = new ArrayList<>();

    @Column(name = "opening_date")
    private LocalDate openingDate;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Movie(String title, String director, String cast, String country, String genre, String runtime, String age, String content, String posterImgPath, List<String> previewImgPath, LocalDate openingDate) {

        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.genre = genre;
        this.runtime = runtime;
        this.age = age;
        this.sumOfRating = 0;
        this.totalRatings = 0;
        this.content = content;
        this.posterImgPath = posterImgPath;
        this.previewImgPath = previewImgPath;
        this.openingDate = openingDate;

    }


}


