package com.example.feelog.Repository;

import com.example.feelog.Entity.Blog;
import com.example.feelog.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@NoRepositoryBean
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByMember(Member member);
}