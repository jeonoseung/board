package com.project.board.Entity;

import com.project.board.Enum.PostState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="post")
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_pid;
    
    @Column(length=100,nullable = false)
    private String post_title;
    @Column(columnDefinition="longtext",nullable = false)
    private String post_content;
    
    @Enumerated(EnumType.STRING)
    @Column(length=25,nullable = false)
    private PostState post_state;
    @ManyToOne
    @JoinColumn(name = "post_category", referencedColumnName = "category_pid",nullable = false)
    private CategoryEntity post_category;
    @Column(length=200)
    private String post_keyword;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime post_create_date;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime post_update_date;

    @Column(nullable = false)
    private Long post_view;

    @ManyToOne
    @JoinColumn(name = "post_author", referencedColumnName = "user_pid",nullable = false)
    private UserEntity post_author;

    public void setTitle(String title){
        this.post_title = title;
    }
    public void setContent(String content){
        this.post_content = content;
    }
    public void setCategory(CategoryEntity category){
        this.post_category = category;
    }
    public void setState(PostState state){
        this.post_state = state;
    }
}
