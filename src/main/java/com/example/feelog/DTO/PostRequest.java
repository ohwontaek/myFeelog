package com.example.feelog.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostRequest {
    private String title;
    private String description;
    private MultipartFile image;


}
