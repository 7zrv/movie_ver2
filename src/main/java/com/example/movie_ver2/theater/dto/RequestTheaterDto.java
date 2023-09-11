package com.example.movie_ver2.theater.dto;

import com.example.movie_ver2.theater.entity.Theater;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestTheaterDto {
    private String area;
    private String address;
    private String number;

    public Theater toEntity() {
        return Theater.builder()
                .area(this.area)
                .address(this.address)
                .number(this.number)
                .build();
    }
}
