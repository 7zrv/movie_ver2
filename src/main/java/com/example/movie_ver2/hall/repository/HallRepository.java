package com.example.movie_ver2.hall.repository;

import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long>  {
    boolean existsByTheaterAndName(Theater theater, String name);
}
