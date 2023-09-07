package com.example.feelog.Repository;

import com.example.feelog.Entity.Like;
import com.example.feelog.Entity.LikeID;
import com.example.feelog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
//@NoRepositoryBean
public interface LikeRepository extends JpaRepository<Like, LikeID> {
    Collection<? extends Like> findAllByPost(Post post);

    void deleteAllByPost(Post post);

    Long countByPost(Post post);
}