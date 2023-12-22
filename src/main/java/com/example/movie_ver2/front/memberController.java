package com.example.movie_ver2.front;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class memberController {


    @RequestMapping("/signup")
    public String showSignupPage(){

        return "memberHtml/signupPage";
    }

    @GetMapping("/login")
    public String showLoginPage(){

        return "memberHtml/login";
    }

    @GetMapping("/myPage")
    public String showMyPage(){

        return "memberHtml/myPage";
    }


}
