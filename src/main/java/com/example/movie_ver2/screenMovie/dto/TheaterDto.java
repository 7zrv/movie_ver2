package com.example.movie_ver2.screenMovie.dto;


import com.example.movie_ver2.theater.entity.Theater;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class TheaterDto {
    private Long id;
    private String local;
    private String area;

    @Builder
    public TheaterDto(Long id, String local, String area) {
        this.id = id;
        this.local = local;
        this.area = area;
    }

    public static TheaterDto of(Theater theater) {
        return TheaterDto.builder()
                .id(theater.getId())
                .local(theater.getAddress().substring(0,2))
                .area(theater.getArea())
                .build();
    }
}
