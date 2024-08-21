package com.project.board;

import com.project.board.DTO.CreateUserRequest;
import com.project.board.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.board.DTO.CreatePostRequest;
import com.project.board.Service.PostService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequiredArgsConstructor
public class APIController {

    private final PostService postService;

    @PostMapping("/post/wirte")
    public ResponseEntity<Void> CreatePost(@Valid @RequestBody CreatePostRequest createPostRequest){
        postService.CreatePost(createPostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<Void> CreateUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        userService.CreateUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

