package com.example.movie_ver2.schedule.service;


import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.hall.repository.HallRepository;
import com.example.movie_ver2.hall.service.HallService;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.service.MovieService;
import com.example.movie_ver2.schedule.dto.*;
import com.example.movie_ver2.schedule.entity.Schedule;
import com.example.movie_ver2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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


    public List<SearchSchedulesResponseDto> searchSchedules(SearchScheduleRequestDto requestDto) {
        List<Schedule> schedules = scheduleRepository.findScheduleByHallIdAndScreenDate(requestDto.getHallId(), requestDto.getScreenDate());

        List<SearchSchedulesResponseDto> responseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            SearchSchedulesResponseDto responseDto = new SearchSchedulesResponseDto();
            responseDto.setStartTime(schedule.getStartTime());
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }

    public SearchScheduleResponseDto searchSchedule(Long scheduleId) {

        return scheduleRepository.findById(scheduleId)
                .map(SearchScheduleResponseDto::of)
                .orElseThrow(() -> new NoSuchElementException("Schedule not found for id: " + scheduleId));

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


    public Schedule findScheduleById(Long scheduleId){
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상영일정은 존재하지 않습니다."));
    }


}
