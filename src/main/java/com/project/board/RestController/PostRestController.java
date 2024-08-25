package com.project.board.RestController;

import com.project.board.DTO.CreatePostRequest;
import com.project.board.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;

    @PostMapping("/post/wirte")
    public ResponseEntity<Void> CreatePost(@Valid @RequestBody CreatePostRequest createPostRequest){
        postService.CreatePost(createPostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}