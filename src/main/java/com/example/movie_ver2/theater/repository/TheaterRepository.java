package com.example.movie_ver2.theater.repository;

import com.example.movie_ver2.theater.entity.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    Boolean existsByArea(String area);
    Boolean existsByAreaAndIdNot(String area, Long id);
    Page<Theater> findByAddressStartingWith(String local, Pageable pageable);
    List<Theater> findByAddressStartingWithOrderByArea(String local);
    Page<Theater> findAll(Pageable pageable);

    List<Theater> findByScreenMovies_Id(Long screenMovieId);
}
