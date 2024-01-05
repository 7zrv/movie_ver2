package com.example.movie_ver2.theater.entity;

import com.example.movie_ver2.screenMovie.entity.ScreenMovies;
import com.example.movie_ver2.theater.dto.RequestTheaterDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "theaters")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id", nullable = false)
    private Long id;

    @Column(name = "theater_area", nullable = false, unique = true)
    private String area;        //지점

    @Column(name = "address")
    private String address;

    @Column(name = "tel_number")
    private String number;

    @Column(name = "total_seats")
    @ColumnDefault("0")
    private Integer seats;      //총 좌석수

    //상영 영화 목록
    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    private List<ScreenMovies> screenMovies = new ArrayList<>();

    @Column(name="reg_date", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime reg_date;

    @Column(name="mod_date")
    @LastModifiedDate
    private LocalDateTime mod_date;

    @Builder
    public Theater(String area, String address, String number, Integer halls, Integer seats) {
        this.area = area;
        this.address = address;
        this.number = number;
        this.seats = seats;
    }

    public void update(RequestTheaterDto requestDto){
        if(StringUtils.hasText(requestDto.getArea()) && !(Objects.equals(this.area, requestDto.getArea()))){
            this.area = requestDto.getArea();
        }
        if(StringUtils.hasText(requestDto.getAddress())){
            this.address = requestDto.getAddress();
        }
        if(StringUtils.hasText(requestDto.getNumber())){
            this.number = requestDto.getNumber();
        }
    }

}
