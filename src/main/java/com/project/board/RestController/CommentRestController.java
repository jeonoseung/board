package com.project.board.RestController;

import com.project.board.DTO.Request.CreateCommentRequest;
import com.project.board.Service.CommentService;
import com.project.board.Utils.JwtToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class CommentRestController {
    private final CommentService commentService;
    private final JwtToken jwtToken;
    
    @PostMapping("/comment")
    public ResponseEntity<Void> CreateComment(
            @Valid @RequestBody CreateCommentRequest createCommentRequest,
            @CookieValue(value="access_token",required = false) String access
    ){
        jwtToken.validateToken(access);
        String user_id = jwtToken.getUserId(access);
        commentService.createComment(createCommentRequest,user_id);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/comment/{comment_pid}")
    public ResponseEntity<Void> DeleteComment(
            @PathVariable(value = "comment_pid",required = false) String comment_pid,
            @CookieValue(value="access_token",required = false) String access
    ){
        String regex = "^\\d+$";
        boolean isNumber = Pattern.matches(regex,comment_pid);
        if(isNumber){
            Long pid = Long.parseLong(comment_pid);
            commentService.deleteComment(pid,access);
        }
        else {
            throw new IllegalArgumentException("삭제하려는 댓글을 다시 확인해주세요.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}