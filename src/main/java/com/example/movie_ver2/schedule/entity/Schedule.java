package com.example.movie_ver2.schedule.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;



@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "hall_id", insertable = false, updatable = false)
    private Hall hall;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

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
    public Schedule(Long id, Long scrId, String movieTitle, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.scrId = scrId;
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
