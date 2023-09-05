package com.example.feelog.Repository;

import com.example.feelog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@NoRepositoryBean
public interface PostRepository extends JpaRepository<Post, Long> {
}