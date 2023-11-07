package com.example.movie_ver2.hall.repository;

import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long>  {
    boolean existsByTheaterAndName(Theater theater, String name);

    boolean existsByTheaterAndNameAndIdNot(Theater theater, String name, Long id);

    List<Hall> findByTheater(Theater theater);

    Long countByTheater(Theater theater);
}
