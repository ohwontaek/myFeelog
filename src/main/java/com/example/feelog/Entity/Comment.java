package com.example.feelog.Entity;



import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;
@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "comment_date", updatable = false, nullable = false)
    private Timestamp commentDate;
}
