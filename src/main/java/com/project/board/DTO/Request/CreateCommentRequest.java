package com.project.board.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private Long target_id;
    @NotBlank(message = "댓글을 입력해 주세요.")
    @Size(min=1,max=200,message = "최소 1자 ~ 최대 200자 입력이 필요합니다.")
    private String comment_content;
    @NotNull(message="비정상적인 접근입니다. ( post_pid null )")
    private Long post_pid;
    
    public String getCommentContent(){
        return comment_content;
    }
    public Long getPostPid(){
        return post_pid;
    }
    public Long getTargetId(){
        return target_id;
    }
}