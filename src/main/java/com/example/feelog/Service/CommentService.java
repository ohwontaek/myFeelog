package com.example.feelog.Service;

import com.example.feelog.Entity.Comment;
import com.example.feelog.Repository.CommentRepository;
import com.example.feelog.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPost(postRepository.findById(postId).get());
    }
}
