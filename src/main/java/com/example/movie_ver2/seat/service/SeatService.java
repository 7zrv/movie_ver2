package com.example.movie_ver2.seat.service;

import com.example.movie_ver2.schedule.entity.Schedule;

import com.example.movie_ver2.schedule.service.ScheduleService;
import com.example.movie_ver2.seat.dto.*;

import com.example.movie_ver2.seat.entity.Seat;
import com.example.movie_ver2.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SeatService {

    private final ScheduleService scheduleService;
    private final SeatRepository seatRepository;

    @Transactional
    public void saveSeats(SaveSeatRequestDto requestDto){

        Schedule schedule = scheduleService.findScheduleById(requestDto.getScheduleId());

        for(int seatRow = 0; seatRow < requestDto.getRow() ;seatRow++ ){
            for(int seatColumn = 0; seatColumn < requestDto.getColumn(); seatColumn++){
                seatRepository.save(requestDto.toEntity(schedule, seatRow, seatColumn));
            }
        }

    }

    @Transactional
    public List<ShowSeatsResponseDto> showSeatList(Long scheduleId) {
        List<Seat> seats = seatRepository.findAllByScheduleId(scheduleId);
        return convertToDtoList(seats);
    }


    private List<ShowSeatsResponseDto> convertToDtoList(List<Seat> seats) {
        return seats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ShowSeatsResponseDto convertToDto(Seat seat) {
        ShowSeatsResponseDto dto = new ShowSeatsResponseDto();
        dto.setSeatId(seat.getId());
        dto.setRow(seat.getRow());
        dto.setColumn(seat.getColumn());
        dto.setPrice(seat.getPrice());
        dto.setReserveAvail(seat.isReserveAvail());

        return dto;
    }

    @Transactional
    public void selectSeats(SelectSeatRequestDto requestDto){

        List<Seat> seats = seatRepository.findAllById(requestDto.getSeatIds());

        for (Seat seat : seats) {
            seat.setReserveAvail(false);
        }

        seatRepository.saveAll(seats);
    }

    @Transactional
    public void soldSeats(SoldSeatsRequestDto requestDto){

        List<Seat> seats = seatRepository.findAllById(requestDto.getSeatIds());

        for (Seat seat : seats) {
            seat.setSoldout(true);
        }

        seatRepository.saveAll(seats);
    }

    @Transactional
    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void verifySeatState() {
        List<Seat> seatsToUpdate = seatRepository.findByReserveAvailFalseAndSoldoutFalse();

        for (Seat seat : seatsToUpdate) {
            seat.setReserveAvail(true);
            seatRepository.save(seat);
        }
    }


    @Transactional
    public void deleteSeats(DeleteSeatRequestDto requestDto){

        List<Long> seatIds = requestDto.getSeatIds();

        seatIds.forEach(seatRepository::deleteById);

    }


}
