package com.example.movie_ver2.schedule.entity;


import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.schedule.dto.ModifyScheduleRequestDto;
import com.example.movie_ver2.seat.entity.Seat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", referencedColumnName = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    private List<Seat> seats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")//, insertable = false, updatable = false
    private Movie movie;

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
    public Schedule(Hall hall, Movie movie, LocalDateTime startTime, LocalDateTime endTime) {
        this.hall = hall;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void modifySchedule(ModifyScheduleRequestDto requestDto){
        this.startTime = requestDto.getStartTime();
        this.endTime = requestDto.getEndTime();

    }

}
