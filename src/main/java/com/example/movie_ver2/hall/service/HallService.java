package com.example.movie_ver2.hall.service;

import com.example.movie_ver2.hall.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepository;
}
