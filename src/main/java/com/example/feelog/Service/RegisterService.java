package com.example.feelog.Service;

import com.example.feelog.DTO.LoginRequest;
import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Entity.Member;
import com.example.feelog.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private final MemberRepository memberRepository;


    public RegisterService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signup(RegisterRequest dto) {
        Member member = new Member(dto);
        memberRepository.save(member);
    }

    public Optional<Member> login(LoginRequest dto) {
        return memberRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
    }

//    public Member register(RegisterRequest request) {
//        if(memberRepository.existsByEmail(request.getEmail())) {
//            throw new RuntimeException();
//        }
//
//        Member member = requestToMember(request);
//        memberRepository.save(member);
//
//        return member;
//    }
//
//    public Member requestToMember(RegisterRequest request) {
//        return new Member(
//        );
//    }



}
