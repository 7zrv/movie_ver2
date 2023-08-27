package com.example.movie_ver2.front.memberFrontend;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class memberController {


    @GetMapping("/signup")
    public String showSignupPage(){


        return "memberHtml/signupPage";
    }
}
