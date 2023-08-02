package com.example.movie_ver2.member.dto;

import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.member.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Getter
public class SignupMemberRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "생년월일 ex) 1990-00-00 을 입력해주세요.")
    private String birthday;

    @NotBlank(message = "성별을 선택해주세요.")
    private String sex;

    public Member toEntity() {
        LocalDate birthdayDate = LocalDate.parse(birthday);
        Sex sexEnum = Sex.valueOf(sex);

        return Member.builder()
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .birthday(birthdayDate)
                .sex(sexEnum)
                .build();
    }
}

