package com.example.feelog.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;


@Entity
@Getter
public class Like {
    @Id
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
}
