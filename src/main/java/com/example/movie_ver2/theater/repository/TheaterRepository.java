package com.example.movie_ver2.theater.repository;

import com.example.movie_ver2.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    Boolean existsByArea(String area);
    List<Theater> findByAddressLike(String local);
}
