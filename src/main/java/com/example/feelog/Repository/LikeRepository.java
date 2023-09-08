package com.example.feelog.Repository;

import com.example.feelog.Entity.PostLike;
import com.example.feelog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
//@NoRepositoryBean
public interface LikeRepository extends JpaRepository<PostLike, Long> {
    Collection<? extends PostLike> findAllByPost(Post post);

    void deleteAllByPost(Post post);

    Long countByPost(Post post);
}