package com.example.feelog.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

@Entity
@Getter
public class Board extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image_url", columnDefinition = "MEDIUMBLOB")
    private byte[] imageUrl;

    public String generateImage() {
        return Base64.encodeBase64String(this.imageUrl);
    }
}
