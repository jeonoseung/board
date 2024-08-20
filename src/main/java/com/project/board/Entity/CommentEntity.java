package com.project.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="comment")
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_pid;
    
    @ManyToOne
    @JoinColumn(name = "comment_post", referencedColumnName = "post_pid",nullable = false)
    private PostEntity comment_post;
    
    @Column(length=255,nullable = false)
    private String comment_content;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime comment_create_date;
    
    @ManyToOne
    @JoinColumn(name = "comment_author", referencedColumnName = "user_pid",nullable = false)
    private UserEntity comment_author;
}