package com.example.feelog.DTO;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

@Getter
@Setter
public class CommentResponse {
    private Long commentId;
    private String MemberName;
    private String commentText;
    private byte[] imageUrl;

    public String generateImage() {
        return Base64.encodeBase64String(this.imageUrl);
    }
}
