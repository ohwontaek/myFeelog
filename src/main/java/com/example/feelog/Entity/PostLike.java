package com.example.feelog.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    public PostLike(Post post, Member member) {
        super();
        this.post = post;
        this.member = member;
    }

    public PostLike() {
    }
}
