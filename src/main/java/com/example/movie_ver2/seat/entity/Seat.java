package com.example.movie_ver2.seat.entity;


import com.example.movie_ver2.schedule.entity.Schedule;
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
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
    private Schedule schedule;

    @Column(name = "seat_row", nullable = false)
    private int row;

    @Column(name = "seat_col", nullable = false)
    private int column;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "reserve_avail", nullable = false)
    private boolean reserveAvail;

    @Column(name = "soldout", nullable = false)
    private boolean soldout;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Builder
    public Seat(Schedule schedule, int row, int column, int price) {
        this.schedule = schedule;
        this.row = row;
        this.column = column;
        this.price = price;
        this.reserveAvail = true;
        this.soldout = false;
    }

    public void setReserveAvail(boolean state){
        this.reserveAvail = state;
    }

    public void setSoldout(boolean state){
        this.soldout = state;
    }

}
