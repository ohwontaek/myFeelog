package com.example.feelog.Repository;

import com.example.feelog.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
//@NoRepositoryBean
public interface MemberRepository extends JpaRepository<Member, Long> {

//    boolean existsByEmail(String email);
}