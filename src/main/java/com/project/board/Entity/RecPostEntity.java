package com.project.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Table(name="post_rec")
@Entity
public class RecPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rec_pid;
    
    @ManyToOne
    @JoinColumn(name="rec_post_pid",nullable = false,referencedColumnName = "post_pid")
    private PostEntity rec_post_pid;
    
    @ManyToOne
    @JoinColumn(name="rec_user_id",nullable = false,referencedColumnName = "user_id")
    private UserEntity rec_user_id;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rec_date;
    
    public void setUserId(UserEntity user){
        this.rec_user_id = user;
    }

    public void setPostPid(PostEntity post){
        this.rec_post_pid = post;
    }

    @PrePersist
    public void setDate(){
        this.rec_date = LocalDateTime.now();
    }
}