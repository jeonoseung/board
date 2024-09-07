package com.project.board.RestController;

import com.project.board.DTO.Request.CreatePostRequest;
import com.project.board.Entity.CategoryEntity;
import com.project.board.Service.CategoryService;
import com.project.board.Service.PostService;
import com.project.board.Utils.JwtToken;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;
    private final JwtToken jwtToken;
    private final CategoryService categoryService;

    @PostMapping("/post")
    public ResponseEntity<Void> CreatePost(
            @Valid @RequestBody CreatePostRequest createPostRequest,
            @CookieValue(value="access_token",required = false) String access
    ) throws IllegalAccessException {

        jwtToken.validateToken(access);
        String user_id = jwtToken.getUserId(access);
        postService.CreatePost(createPostRequest,user_id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryEntity>> GetCategory(){

        List<CategoryEntity> category = categoryService.getCategoryList();

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

}