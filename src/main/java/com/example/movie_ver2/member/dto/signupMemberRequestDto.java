package com.example.movie_ver2.member.dto;

import com.example.movie_ver2.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class signupMemberRequestDto {

    private String title;
    private String content;

    //실제 엔티티로 컨버팅
    public Member toEntity(){
        return Member.builder()
                .title(title)
                .content(content)
                .build();
    }
}
