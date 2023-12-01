package com.example.movie_ver2.seat.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatsResponseDto {

    private Long SeatId;
    private int row;
    private int column;
    private int price;
    private boolean reserveAvail;
}
