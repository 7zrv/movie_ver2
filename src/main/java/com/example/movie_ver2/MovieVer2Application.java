package com.example.movie_ver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class MovieVer2Application {

    public static void main(String[] args) {
        SpringApplication.run(MovieVer2Application.class, args);
    }

}
