package com.example.movie_ver2.theater.service;

import com.example.movie_ver2.hall.repository.HallRepository;
import com.example.movie_ver2.theater.dto.RequestTheaterDto;
import com.example.movie_ver2.theater.dto.TheaterAreaDto;
import com.example.movie_ver2.theater.dto.TheaterInfoDto;
import com.example.movie_ver2.theater.entity.Theater;
import com.example.movie_ver2.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final HallRepository hallRepository;

    @Transactional
    public Theater saveTheater(RequestTheaterDto requestDto) {
        return theaterRepository.save(requestDto.toEntity());
    }

    @Transactional
    public void updateTheater(Long id, RequestTheaterDto requestDto) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화관은 존재하지 않습니다."));
        theater.update(requestDto);
    }

    @Transactional
    public void delete(Long id) {
        try{
            theaterRepository.deleteById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("해당 영화관은 존재하지 않습니다.");
        }
    }

    public boolean checkDuplicate(String area) {
        return theaterRepository.existsByArea(area);
    }

    public boolean checkDuplicateByIdNot(String area, Long id) {
        return theaterRepository.existsByAreaAndIdNot(area, id);
    }

    public List<TheaterInfoDto> getAll() {
        return theaterRepository.findAll().stream()
                .map((Theater theater) -> TheaterInfoDto.of(theater, hallRepository.countByTheater(theater)))
                .collect(Collectors.toList());
    }

    public List<TheaterAreaDto> getAllArea() {
        return theaterRepository.findAll().stream()
                .map(TheaterAreaDto::of)
                .collect(Collectors.toList());
    }

    public TheaterInfoDto getTheaterInfoById(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화관은 존재하지 않습니다."));
        Long countHalls = hallRepository.countByTheater(theater);
        return TheaterInfoDto.of(theater, countHalls);
    }


    public List<TheaterAreaDto> getTheatersByLocal(String local) {
        return theaterRepository.findByAddressStartingWith(local).stream()
                .map(TheaterAreaDto::of)
                .collect(Collectors.toList());
    }

}
