package com.example.feelog.Entity;

import com.example.feelog.DTO.PostRequest;
import jakarta.persistence.*;
import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.IOException;

@Entity
@Getter
@Setter
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public Post(PostRequest postDTO, Member member, Blog blog) {
        super();
        this.member = member;
        this.blog = blog;
        this.title = postDTO.getTitle();
        this.description = postDTO.getDescription();
        try{
            if(!postDTO.getImage().isEmpty()) {
                this.imageUrl = postDTO.getImage().getBytes();
            }else{
                System.out.println("empty image");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Post(){

    }

    public String generateImage() {
        return Base64.encodeBase64String(this.imageUrl);
    }
}
