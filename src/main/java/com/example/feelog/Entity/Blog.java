package com.example.feelog.Entity;


import com.example.feelog.DTO.BlogRequest;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;


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
    @Column(name = "image_url")
    private String imageUrl;

    public Blog(BlogRequest dto, Member member) {
        this.member = member;
        this.title = dto.getTitle();
        this.introduce = dto.getIntroduce();
        this.imageUrl = dto.getImage();
    }

    public Blog() {
    }
}
