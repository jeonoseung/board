package com.project.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Table(name="comment_rec")
@Entity
public class RecCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rec_pid;
    
    @ManyToOne
    @JoinColumn(name="rec_comment_pid",nullable = false,referencedColumnName = "comment_pid")
    private CommentEntity rec_comment_pid;
    
    @ManyToOne
    @JoinColumn(name="rec_user_id",nullable = false,referencedColumnName = "user_id")
    private UserEntity rec_user_id;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rec_date;

    @PrePersist
    public void setDate(){
        this.rec_date = LocalDateTime.now();
    }
}