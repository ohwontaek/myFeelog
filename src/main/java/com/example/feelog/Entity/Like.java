package com.example.feelog.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
public class Like {
    @Id
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
