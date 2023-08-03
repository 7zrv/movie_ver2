package com.example.movie_ver2.member.service;


import com.example.movie_ver2.member.dto.ModifyPasswordRequestDto;
import com.example.movie_ver2.member.dto.ModifyPhoneNumberDto;
import com.example.movie_ver2.member.dto.SignupMemberRequestDto;
import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.member.exception.DuplicateEmailException;
import com.example.movie_ver2.member.exception.NoSuchMemberException;
import com.example.movie_ver2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchMemberException("Member not found with ID: " + id));
    }


    @Transactional
    public Member modifyPassword(Long id, ModifyPasswordRequestDto requestDto){
        Member member = memberRepository.findById(id).
                orElseThrow(() -> new NoSuchMemberException("Memeber not found with ID:" + id));

        member.updatePassword(requestDto.getPassword());

        return member;
    }

    @Transactional
    public Member modifyPhoneNumber(Long id, ModifyPhoneNumberDto requestDto){
        Member member = memberRepository.findById(id).
                orElseThrow(() -> new NoSuchMemberException("Memeber not found with ID:" + id));

        member.updatePhoneNumber(requestDto.getPhoneNumber());

        return member;
    }


    public void delete(Long id){
        memberRepository.deleteById(id);
    }



}
