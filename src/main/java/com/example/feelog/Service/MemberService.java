package com.example.feelog.Service;

import com.example.feelog.DTO.LoginRequest;
import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Entity.Member;
import com.example.feelog.Repository.MemberRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {


    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    public Optional<Member> login(LoginRequest dto) {
        return MemberRepository.findByMemberIdAndMemberPwd(dto.getEmail(), dto.getPassword());
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
