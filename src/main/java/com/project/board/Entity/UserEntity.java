package com.project.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Table(name="user")
@Entity
@Getter
@Setter
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_pid;
    
    @Column(length=50,nullable = false, unique = true)
    private String user_id;
    
    @Column(length=255,nullable = false)
    private String user_pass;
    
    @Column(length=50,nullable = false, unique = true)
    private String user_nickname;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime user_create_date;


    public void setUserId(String id){
        this.user_id = id;
    }
    public void setUserPass(String pass){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.user_pass = passwordEncoder.encode(pass);
    }
    public void setUserNickname(String nickname){
        this.user_nickname = nickname;
    }
    @PrePersist
    public void setCreateDate(){
        this.user_create_date = LocalDateTime.now();
    }
}