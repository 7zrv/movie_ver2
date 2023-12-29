package com.example.movie_ver2.front;


import com.example.movie_ver2.member.dto.SignupMemberRequestDto;
import com.example.movie_ver2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class memberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String showSignupPag(SignupMemberRequestDto requestDto){

        return "memberHtml/signupPage";
    }

    @PostMapping("/signupMember")
    public String signupMember(@ModelAttribute SignupMemberRequestDto requestDto){
        memberService.save(requestDto);

        return "redirect:/";
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
