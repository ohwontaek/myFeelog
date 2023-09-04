package com.example.feelog.Entity;


import com.example.feelog.DTO.BlogRequest;
import jakarta.persistence.*;

import java.io.IOException;
import java.sql.Timestamp;
import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;


@Entity
@Getter
public class Blog extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "introduce")
    private String introduce;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image_url", columnDefinition = "MEDIUMBLOB")
    private byte[] imageUrl;

    public String generateImage() {
        return Base64.encodeBase64String(this.imageUrl);
    }

    public Blog(BlogRequest dto, Member member) {
        this.member = member;
        this.title = dto.getTitle();
        this.introduce = dto.getIntroduce();
        try {
            if(!dto.getImage().isEmpty()) {
                this.imageUrl = dto.getImage().getBytes();
            }else{
                System.out.println("empty image");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Blog() {
    }
}
