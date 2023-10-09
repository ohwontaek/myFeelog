package com.example.feelog.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private Long member_id;
    private Long post_id;
    private String commentText;

    @Override
    public String toString() {
        return "CommentRequest{" +
                "member_id=" + member_id +
                ", post_id=" + post_id +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
