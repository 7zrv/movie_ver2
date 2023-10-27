package com.example.movie_ver2.schedule.service;


import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.hall.repository.HallRepository;
import com.example.movie_ver2.hall.service.HallService;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.service.MovieService;
import com.example.movie_ver2.schedule.dto.CreateScheduleRequestDto;
import com.example.movie_ver2.schedule.dto.ScheduleSearchRequestDto;
import com.example.movie_ver2.schedule.entity.Schedule;
import com.example.movie_ver2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final HallRepository hallRepository;
    private final ScheduleRepository scheduleRepository;
    private final MovieService movieService;
    private final HallService hallService;

    public void saveSchedule(CreateScheduleRequestDto requestDto) {

        Movie movie = movieService.findMovieById(requestDto.getMovieId());
        Hall hall = hallService.findHallById(requestDto.getHallId());

        scheduleRepository.save(requestDto.toEntity(movie, hall));
    }


    public List<Schedule> searchSchedules(ScheduleSearchRequestDto requestDto) {
        return scheduleRepository.findScheduleByHallIdAndScreenDate(requestDto.getHallId(), requestDto.getScreenDate());
    }
}
