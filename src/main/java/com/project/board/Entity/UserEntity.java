package com.project.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Table(name="user")
@Entity
@Getter
@Setter
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_pid;
    
    @Column(length=50,nullable = false)
    private String user_id;
    
    @Column(length=50,nullable = false)
    private String user_pass;
    
    @Column(length=50,nullable = false)
    private String user_nickname;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime user_create_date;
}