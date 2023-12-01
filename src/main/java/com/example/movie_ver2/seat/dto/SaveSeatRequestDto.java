package com.example.movie_ver2.seat.dto;

import com.example.movie_ver2.schedule.entity.Schedule;
import com.example.movie_ver2.seat.entity.Seat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSeatRequestDto {
    private Long scheduleId;
    private int row;
    private int column;
    private int price;

    public Seat toEntity(Schedule schedule, int seatRow, int seatColumn) {
        return Seat.builder()
                .schedule(schedule)
                .row(seatRow)
                .column(seatColumn)
                .price(price)
                .build();
    }

}
