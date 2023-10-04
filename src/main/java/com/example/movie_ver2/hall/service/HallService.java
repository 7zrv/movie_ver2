package com.example.movie_ver2.hall.service;

import com.example.movie_ver2.hall.dto.HallInfoDto;
import com.example.movie_ver2.hall.dto.RequestHallDto;
import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.hall.repository.HallRepository;
import com.example.movie_ver2.theater.entity.Theater;
import com.example.movie_ver2.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepository;
    private final TheaterRepository theaterRepository;

    @Transactional
    public Hall saveHall(Long theaterId, RequestHallDto requestDto) {
        try{
            Theater theater = theaterRepository.getReferenceById(theaterId);
            return hallRepository.save(requestDto.toEntity(theater));
        }catch (Exception e){
            throw new EntityNotFoundException("해당 영화관은 존재하지 않습니다.");
        }
    }

    @Transactional
    public void updateHall(Long id, RequestHallDto hallDto) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상영관은 존재하지 않습니다."));
        hall.update(hallDto);
    }

    @Transactional
    public void delete(Long id) {
        try{
            hallRepository.deleteById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("해당 상영관은 존재하지 않습니다.");
        }
    }

    public boolean checkDuplicate(Long theaterId, String name) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화관은 존재하지 않습니다."));
        return hallRepository.existsByTheaterAndName(theater, name);
    }

    public HallInfoDto getHallInfoById(Long id) {
        return HallInfoDto.of(hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상영관은 존재하지 않습니다.")));
    }

    public List<HallInfoDto> getHallsByTheater(Long theaterId) {
        //영화관 총 좌석 수 계산
        return null;
    }
}
