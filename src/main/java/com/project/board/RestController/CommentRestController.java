package com.project.board.RestController;

import com.project.board.DTO.Request.CreateCommentRequest;
import com.project.board.Service.CommentService;
import com.project.board.Utils.JwtToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}