package com.example.movie_ver2.seat.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelectSeatRequestDto {

    private List<Long> seatIds;

}
