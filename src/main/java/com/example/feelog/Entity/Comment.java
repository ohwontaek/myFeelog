package com.example.feelog.Entity;



import com.example.feelog.DTO.CommentRequest;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "member_id", nullable = true)
    private Member member;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    public Comment(){}

    public Comment(Member member, Post post, String commentText) {
        super();
        this.member = member;
        this.post = post;
        this.commentText = commentText;
    }

}
