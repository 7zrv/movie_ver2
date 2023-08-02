package com.example.movie_ver2.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateMemberRequestDto {
    private String password;
    private String phoneNumber;

}

