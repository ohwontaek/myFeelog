package com.example.feelog.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BlogRequest {
    private String title;
    private String introduce;
    private MultipartFile image;
}
