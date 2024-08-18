package com.project.board.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreatePostRequest {
    @NotBlank(message = "게시글 제목을 입력해 주세요.")
    @Size(min=5,max=50,message="게시글 제목은 최소 5자 ~ 최대 50자까지 입력이 가능합니다.")
    private String post_title;
    @NotBlank(message = "게시글 내용을 입력해 주세요.")
    @Size(min=5,message = "게시글 내용은 최소 5자 이상 입력이 필요합니다.")
    private String post_content;
    @NotNull(message="카테고리 입력이 필요합니다.")
    private Long post_category;
    private String[] post_keyword;

    public String GetPostTitle(){
        return post_title;
    }

    public String GetPostContent(){
        return post_content;
    }
    public Long GetPostCategory(){
        return post_category;
    }
    public String GetPostKeyword(){
        if(post_keyword != null && post_keyword.length > 0){
            return String.join(",",post_keyword);
        }
        else {
            return "";
        }
    }

}
