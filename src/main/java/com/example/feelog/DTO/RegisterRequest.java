package com.example.feelog.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Data
@Getter
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String introduce;

    public RegisterRequest(String email, String password, String name, String introduce) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.introduce = introduce;
    }
}

