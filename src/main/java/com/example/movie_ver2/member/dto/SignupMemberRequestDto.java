package com.example.movie_ver2.member.dto;

import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.core.enums.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class SignupMemberRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야합니다.")
    private String password;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNumber;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "성별을 선택해주세요.")
    private Sex sex;

    public Member toEntity() {

        return Member.builder()
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .birthday(birthday)
                .sex(sex)
                .build();
    }
}

