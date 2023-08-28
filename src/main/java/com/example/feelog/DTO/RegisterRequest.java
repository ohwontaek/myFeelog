package com.example.feelog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String introduce;
}

