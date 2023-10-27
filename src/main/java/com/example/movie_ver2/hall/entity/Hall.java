package com.example.movie_ver2.hall.entity;

import com.example.movie_ver2.hall.dto.RequestHallDto;
import com.example.movie_ver2.schedule.entity.Schedule;
import com.example.movie_ver2.theater.entity.Theater;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "halls", uniqueConstraints = {@UniqueConstraint(columnNames = {"theater_id", "hall_name"})})
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id", nullable = false)
    private Long id;

    @Column(name = "hall_name", nullable = false)
    private String name;

    @Column(name = "hall_floor")
    private Integer floor;      //상영관 위치(B1층)

    @Column(name = "hall_seats")
    private Integer seats;      //보유 좌석수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="theater_id", nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.PERSIST)
    private List<Schedule> schedules;

    @Column(name="reg_date", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime reg_date;

    @Column(name="mod_date")
    @LastModifiedDate
    private LocalDateTime mod_date;

    @Builder
    public Hall(String name, Integer floor, Integer seats, Theater theater) {
        this.name = name;
        this.floor = floor;
        this.seats = seats;
        this.theater = theater;
    }

    public void update(RequestHallDto requestDto){
        if(StringUtils.hasText(requestDto.getName())){
            this.name = requestDto.getName();
        }
        if(requestDto.getFloor() != null){
            this.floor = requestDto.getFloor();
        }
        if(requestDto.getSeats() != null){
            this.seats = requestDto.getSeats();
        }
    }

}
