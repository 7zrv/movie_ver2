package com.example.movie_ver2.member.dto;

import com.example.movie_ver2.member.entity.Member;
import lombok.Getter;


@Getter
public class MemberResponseDto {
    private String email;
    private String phoneNumber;


    public MemberResponseDto(Member member){
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
    }
}
