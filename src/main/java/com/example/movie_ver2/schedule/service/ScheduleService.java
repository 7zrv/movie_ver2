package com.example.movie_ver2.schedule.service;


import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.hall.service.HallService;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.service.MovieService;
import com.example.movie_ver2.schedule.dto.CreateScheduleRequestDto;
import com.example.movie_ver2.schedule.entity.Schedule;
import com.example.movie_ver2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final MovieService movieService;
    private final HallService hallService;


    private final ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(CreateScheduleRequestDto requestDto) {
        Movie movie = movieService.findMovieById(requestDto.getMovieId());
        Hall hall = hallService.getHallInfoById(requestDto.getHallId());

        return scheduleRepository.save(requestDto.toEntity(movie, ));
    }




}
