package com.example.movie_ver2.member.controller;


import com.example.movie_ver2.member.dto.MemberResponseDto;
import com.example.movie_ver2.member.dto.SignupMemberRequestDto;
import com.example.movie_ver2.member.dto.UpdateMemberRequestDto;
import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.member.exception.ApiResponse;
import com.example.movie_ver2.member.exception.DuplicateEmailException;
import com.example.movie_ver2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/member/signupMember")
    public ResponseEntity<ApiResponse<Map<String, Object>>> signupMember(@RequestBody @Valid SignupMemberRequestDto requestDto, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "회원 가입 실패", null));
        }

        try {
            Member savedMember = memberService.save(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "회원 가입 성공", Map.of("member", savedMember)));
        } catch (DuplicateEmailException ex) {

            Map<String, Object> errorData = new HashMap<>();
            errorData.put("errCode", "duplicate_email");
            errorData.put("errMsg",ex.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "중복된 이메일", errorData));
        }
    }


//    //all 조회
//    @GetMapping("/api/latest/articles")
//    public ResponseEntity<List<ArticleResponseDto>> findAllArticles(){
//
//        List<ArticleResponseDto> articles = blogService.findAll()
//                .stream()
//                .map(ArticleResponseDto::new)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok().body(articles);
//
//    }

    //단건 조회
    @GetMapping("/api/member/getMyInfo/{memberId}")
    public ResponseEntity<MemberResponseDto> getMyInfo(@PathVariable Long memberId){
        return ResponseEntity.ok()
                .body(new MemberResponseDto(memberService.findById(memberId)));
    }


    @DeleteMapping("/api/member/unsignupMember/{memberId}")
    public ResponseEntity<Void> unsignupMember(@PathVariable Long memberId){
        memberService.delete(memberId);

        return ResponseEntity.ok().build();
    }

//    @PutMapping("/api/member/findPassword/{memberId}")
//    public ResponseEntity<Member> findPassword(@PathVariable Long memberId,
//                                               UpdateMemberRequestDto requestDto){
//
//
//    }




}
