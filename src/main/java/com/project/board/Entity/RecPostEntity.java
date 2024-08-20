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
    @JoinColumn(name="rec_user_pid",nullable = false,referencedColumnName = "user_pid")
    private UserEntity rec_user_pid;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rec_date;
    
}