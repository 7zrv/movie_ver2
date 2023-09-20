package com.example.movie_ver2.schedule.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

import java.time.LocalDateTime;



@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "schedules")
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long id;

    @Column(name = "screenroom_id", nullable = false)
    private Long scrId;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "current_seat_stat")
    private int currentSeatStat;


    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Schedule(Long id, Long scrId, String movieTitle, int currentSeatStat, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.scrId = scrId;
        this.movieTitle = movieTitle;
        this.currentSeatStat = currentSeatStat;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
