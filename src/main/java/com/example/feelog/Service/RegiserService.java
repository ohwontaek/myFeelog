package com.example.feelog.Service;

import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Entity.Member;
import com.example.feelog.Repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class RegiserService {

    private final MemberRepository memberRepository;

    public RegiserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
