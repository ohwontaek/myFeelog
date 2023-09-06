package com.example.feelog.Entity;



import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "comment_date", updatable = false, nullable = false)
    private Timestamp commentDate;
}
