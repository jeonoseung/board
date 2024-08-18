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

@Entity
@Table(name="post")
@Getter
@Setter
public class PostEntity {
    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_pid;
    
    @Column(length=100)
    private String post_title;
    @Column(columnDefinition="longtext")
    private String post_content;
    
    @Enumerated(EnumType.STRING)
    @Column(length=25)
    private PostState post_state;
    @ManyToOne
    @JoinColumn(name = "post_category", referencedColumnName = "category_pid")
    private CategoryEntity category;
    @Column(length=200)
    private String post_keyword;
    
    public void setTitle(String title){
        this.post_title = title;
    }
    public void setContent(String content){
        this.post_content = content;
    }
    public void setCategory(CategoryEntity category){
        this.category = category;
    }
    public void setState(PostState state){
        this.post_state = state;
    }
}
