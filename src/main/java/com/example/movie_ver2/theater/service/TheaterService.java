package com.example.movie_ver2.theater.service;

import com.example.movie_ver2.hall.repository.HallRepository;
import com.example.movie_ver2.screenMovie.repository.ScreenMovieRepository;
import com.example.movie_ver2.theater.dto.RequestTheaterDto;
import com.example.movie_ver2.theater.dto.TheaterAreaDto;
import com.example.movie_ver2.theater.dto.TheaterInfoByScreenMovieResponseDto;
import com.example.movie_ver2.theater.dto.TheaterInfoDto;
import com.example.movie_ver2.theater.entity.Theater;
import com.example.movie_ver2.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final ScreenMovieRepository screenMovieRepository;
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

    public Page<TheaterInfoDto> getAll(Pageable pageable) {
        return theaterRepository.findAll(pageable)
                .map((Theater theater) -> TheaterInfoDto.of(theater, hallRepository.countByTheater(theater), screenMovieRepository.countByTheater(theater)));
    }

    public List<TheaterAreaDto> getAllArea() {
        return theaterRepository.findAll().stream()
                .map(TheaterAreaDto::of)
                .collect(Collectors.toList());
    }

    public List<TheaterAreaDto> getAreaByLocal(String local) {
        return theaterRepository.findByAddressStartingWithOrderByArea(local).stream()
                .map(TheaterAreaDto::of)
                .collect(Collectors.toList());
    }

    public TheaterInfoDto getTheaterInfoById(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화관은 존재하지 않습니다."));
        Long countHalls = hallRepository.countByTheater(theater);
        Long countMovies = screenMovieRepository.countByTheater(theater);
        return TheaterInfoDto.of(theater, countHalls, countMovies);
    }


    public Page<TheaterInfoDto> getTheatersByLocal(String local, Pageable pageable) {
        return theaterRepository.findByAddressStartingWith(local, pageable)
                .map((Theater theater) -> TheaterInfoDto.of(theater, hallRepository.countByTheater(theater), screenMovieRepository.countByTheater(theater)));
    }

    public List<TheaterInfoByScreenMovieResponseDto> getTheaterInfoByScreenMovieId(Long screenMovieId) {
        return theaterRepository.findByScreenMovies_Id(screenMovieId)
                .stream()
                .map(TheaterInfoByScreenMovieResponseDto::of)
                .collect(Collectors.toList());
    }


}
