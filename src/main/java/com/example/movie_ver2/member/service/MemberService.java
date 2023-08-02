package com.example.movie_ver2.member.service;


import com.example.movie_ver2.member.dto.SignupMemberRequestDto;
import com.example.movie_ver2.member.dto.UpdateMemberRequestDto;
import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.member.exception.DuplicateEmailException;
import com.example.movie_ver2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(SignupMemberRequestDto requestDto){
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateEmailException("이미 가입된 이메일 입니다: " + requestDto.getEmail());
        }
        return memberRepository.save(requestDto.toEntity());
    }

    //단건 조회
    public Member findById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("member not exist! : " + id));
    }

    //delete
    public void delete(Long id){
        memberRepository.deleteById(id);
    }



}
