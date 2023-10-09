package com.example.feelog.Entity;


import com.example.feelog.DTO.RegisterRequest;
import jakarta.persistence.*;
import java.security.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@Setter
public class Member extends BaseTimeEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id", nullable = true)
        private Long memberId;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "picture")
        private String picture;

        @Column(name = "introduce")
        private String introduce;

//        public Member(Long memberId, String name, String email, String password, String picture,
//            String introduce, Timestamp createdAt, Timestamp updatedAt) {
//                this.memberId = memberId;
//                this.name = name;
//                this.email = email;
//                this.password = password;
//                this.picture = picture;
//                this.introduce = introduce;
//                this.createdAt = createdAt;
//                this.updatedAt = updatedAt;
//        }

        public Member(RegisterRequest registerRequest) {
                this.name = registerRequest.getName();
                this.email = registerRequest.getEmail();
                this.password = registerRequest.getPassword();
                this.introduce = registerRequest.getIntroduce();
        }

        public Member() {
        }

}
