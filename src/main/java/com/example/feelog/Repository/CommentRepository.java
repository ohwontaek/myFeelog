package com.example.feelog.Repository;

import com.example.feelog.Entity.Comment;
import com.example.feelog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
//@NoRepositoryBean
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    void deleteAllByPost(Post post);
}