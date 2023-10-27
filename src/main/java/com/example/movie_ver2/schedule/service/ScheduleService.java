package com.example.movie_ver2.schedule.service;


import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.hall.repository.HallRepository;
import com.example.movie_ver2.hall.service.HallService;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.service.MovieService;
import com.example.movie_ver2.schedule.dto.CreateScheduleRequestDto;
import com.example.movie_ver2.schedule.dto.ModifyScheduleRequestDto;
import com.example.movie_ver2.schedule.dto.SearchScheduleRequestDto;
import com.example.movie_ver2.schedule.dto.SearchScheduleResponseDto;
import com.example.movie_ver2.schedule.entity.Schedule;
import com.example.movie_ver2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<SearchScheduleResponseDto> searchSchedules(SearchScheduleRequestDto requestDto) {
        List<Schedule> schedules = scheduleRepository.findScheduleByHallIdAndScreenDate(requestDto.getHallId(), requestDto.getScreenDate());

        List<SearchScheduleResponseDto> responseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            SearchScheduleResponseDto responseDto = new SearchScheduleResponseDto();
            responseDto.setStartTime(schedule.getStartTime());
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }

    public void modifySchedule(ModifyScheduleRequestDto requestDto){
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(IllegalArgumentException::new);
        System.out.println(schedule.getId());
        schedule.modifySchedule(requestDto);
        scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }


}
