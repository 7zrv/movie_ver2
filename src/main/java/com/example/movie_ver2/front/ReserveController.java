package com.example.movie_ver2.front;


import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.theater.dto.TheaterAreaDto;
import com.example.movie_ver2.theater.entity.Theater;
import com.example.movie_ver2.theater.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReserveController {

    private final TheaterService theaterService;

    @GetMapping("/reserve")
    public String showReservePage(Model model){

        List<TheaterAreaDto> theaters = theaterService.getALl();
        model.addAttribute("theaters", theaters);

        return "reserveHtml/selectSchedule";
    }

    @GetMapping("/selectPersonnel")
    public String showPersonnelPage(){

        return "reserveHtml/selectPersonnelSeat";
    }

    @GetMapping("/payment")
    public String showPaymentPage(){

        return "reserveHtml/paymentPage";
    }
}
