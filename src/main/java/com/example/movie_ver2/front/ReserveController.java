package com.example.movie_ver2.front;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {


    @GetMapping("/reserve")
    public String showReservePage(){

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
