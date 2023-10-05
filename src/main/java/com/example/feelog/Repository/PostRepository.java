package com.example.feelog.Repository;

import com.example.feelog.Entity.Blog;
import com.example.feelog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@NoRepositoryBean
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBlog(Blog blog);

    List<Post> findTop3ByOrderByLikesDesc(int limit);
}