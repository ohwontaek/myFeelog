package com.example.feelog.Service;

import com.example.feelog.DTO.LoginRequest;
import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Entity.*;
import com.example.feelog.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final BlogRepository blogRepository;
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private  final LikeRepository likeRepository;
    public RegisterService(MemberRepository memberRepository, BlogRepository blogRepository, PostRepository postRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.memberRepository = memberRepository;
        this.blogRepository = blogRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
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
    @Transactional
    public void deleteMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);


        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            for(Blog blog : blogRepository.findAllByMember(member)) { //멤버가 가진 블로그 모두 조회
                for(Post post : postRepository.findAllByBlog(blog)){// blog에 작성된 글 모두 조회
                    commentRepository.deleteAllByPost(post); //글,좋아요 모두 삭제 후 게시글 삭제
                    likeRepository.deleteAllByPost(post);
                    postRepository.delete(post);
                }
                blogRepository.delete(blog); // 블로그 삭제
            }
            memberRepository.delete(member); // 멤버 삭제
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
