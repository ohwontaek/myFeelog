package com.example.feelog.Service;

import com.example.feelog.DTO.CommentRequest;
import com.example.feelog.DTO.CommentResponse;
import com.example.feelog.Entity.Comment;
import com.example.feelog.Entity.Member;
import com.example.feelog.Repository.CommentRepository;
import com.example.feelog.Repository.MemberRepository;
import com.example.feelog.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberRepository memberRepository;

    public List<CommentResponse> getCommentsByPostId(Long postId) {

        List<Comment> comments = commentRepository.findAllByPost(postRepository.findById(postId).get());
        List<CommentResponse> commentRList = new ArrayList<>();

        for(Comment comment : comments){
            CommentResponse c = new CommentResponse();
            c.setCommentId(comment.getCommentId());
            c.setMemberName(comment.getMember().getName());
            c.setCommentText(comment.getCommentText());
            commentRList.add(c);
        }


        return commentRList;
    }

    public Comment insertComment(CommentRequest comment){
        Comment commentEntiry;
        if(comment.getMember_id() == null){
            commentEntiry = new Comment(null,
                    postRepository.findById(comment.getPost_id()).get(),
                    comment.getCommentText());
        } else {
            commentEntiry = new Comment(memberRepository.findById(comment.getMember_id()).get(),
                    postRepository.findById(comment.getPost_id()).get(),
                    comment.getCommentText());
        }

        return commentRepository.save(commentEntiry);

    }
}
