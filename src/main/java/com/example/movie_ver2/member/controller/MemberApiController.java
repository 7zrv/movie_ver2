package com.example.movie_ver2.member.controller;


import com.example.movie_ver2.member.dto.ModifyPasswordRequestDto;
import com.example.movie_ver2.member.dto.ModifyPhoneNumberDto;
import com.example.movie_ver2.member.dto.SignupMemberRequestDto;
import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.member.dto.ApiResponse;
import com.example.movie_ver2.member.exception.DuplicateEmailException;
import com.example.movie_ver2.member.exception.NoSuchMemberException;
import com.example.movie_ver2.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import net.nurigo.sdk.message.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


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

    @GetMapping("/api/member/getMyInfo/{memberId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getMyInfo(@PathVariable Long memberId) {
        try {
            Member foundMember = memberService.findById(memberId);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("member", foundMember);
            return ResponseEntity.ok().body(new ApiResponse<>(1, "회원 정보 조회 성공", responseData));
        } catch (NoSuchMemberException ex) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("errCode", "member_not_found");
            errorData.put("errMsg", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(0, "회원 정보 조회 실패", errorData));
        }
    }

    @PatchMapping("/api/member/ModifyMyPassword/{memberId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> modifyMyPassword(@PathVariable Long memberId,
                                                                             @RequestBody @Valid ModifyPasswordRequestDto requestDto,
                                                                             Errors errors){

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "비밀번호 형식이 올바르지 않습니다.", null));
        }

        try {
            Member modifiedMember = memberService.modifyPassword(memberId, requestDto);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("member", modifiedMember);
            return ResponseEntity.ok().body(new ApiResponse<>(1, "비밀번호 변경 성공", responseData));
        } catch (NoSuchMemberException ex) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("errCode", "member_not_found");
            errorData.put("errMsg", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(0, "회원 정보 조회 실패", errorData));
        }

    }

    @PatchMapping("/api/member/ModifyMyPhoneNumber/{memberId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> modifyMyPhoneNumber(@PathVariable Long memberId,
                                                                             @RequestBody @Valid ModifyPhoneNumberDto requestDto,
                                                                             Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "휴대폰 번호 형식이 올바르지 않습니다.", null));
        }

        try {
            Member modifiedMember = memberService.modifyPhoneNumber(memberId, requestDto);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("member", modifiedMember);
            return ResponseEntity.ok().body(new ApiResponse<>(1, "휴대폰 번호 변경 성공", responseData));
        } catch (NoSuchMemberException ex) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("errCode", "member_not_found");
            errorData.put("errMsg", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(0, "회원 정보 조회 실패", errorData));
        }

    }

    @DeleteMapping("/api/member/withdrawMember/{memberId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> withdrawMember(@PathVariable Long memberId){
        try {
            memberService.deleteMember(memberId);
            return ResponseEntity.ok().body(new ApiResponse<>(1, "회원탈퇴 성공", null));
        } catch (RuntimeException ex){
            return ResponseEntity.ok().body(new ApiResponse<>(0, "회원탈퇴 실패", null));
        }
    }

//    @PostMapping("/api/member/findId")
//    public SingleMessageSentResponse sendMmsByResourcePath(@RequestBody String phoneNumber) throws IOException {
//
//        ThreadLocalRandom rand = ThreadLocalRandom.current();
//        StringBuilder authNum = new StringBuilder();
//        int numDigits = 6;
//
//        for (int i = 0; i < numDigits; i++) {
//            authNum.append(rand.nextInt(10));
//        }
//
//        Message message = new Message();
//        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
//        message.setFrom("01051636609");
//        message.setTo(phoneNumber);
//        message.setText("[TEST] 인증번호["+ authNum +"]를 입력해주세요.");
//
//
//        // 여러 건 메시지 발송일 경우 send many 예제와 동일하게 구성하여 발송할 수 있습니다.
//        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
//        System.out.println(response);
//
//        return response;
//    }


}
