package com.example.feelog.Service;

import com.example.feelog.DTO.LoginRequest;
import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Entity.Member;
import com.example.feelog.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RegisterService {


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

    @Transactional
    public Member updateMember(Long memberId, String newName, String newEmail, String newPassword, String newIntroduce) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isPresent()) {
            Member existingMember = optionalMember.get();

            if (!newName.isEmpty()) {
                existingMember.setName(newName);
            }

            if (!newEmail.isEmpty()) {
                existingMember.setEmail(newEmail);
            }

            if (!newPassword.isEmpty()) {
                existingMember.setPassword(newPassword);
            }

            if (!newIntroduce.isEmpty()) {
                existingMember.setIntroduce(newIntroduce);
            }

            return memberRepository.save(existingMember);
        } else {
            throw new IllegalArgumentException("해당 회원을 찾을 수 없습니다.");
        }
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

    public Optional<Member> getById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
