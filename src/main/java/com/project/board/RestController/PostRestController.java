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
import java.util.regex.Pattern;

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

    @DeleteMapping("/post/{post_pid}")
    public ResponseEntity<Void> DeletePost(
            @PathVariable(value="post_pid",required = false) String post_pid,
            @CookieValue(value="access_token",required = false) String access
    ){
        String regex = "^\\d+$";
        boolean isNumber = Pattern.matches(regex,post_pid);
        if(isNumber){
            Long pid = Long.parseLong(post_pid);
            postService.deletePost(pid,access);
        }
        else {
            throw new IllegalArgumentException("삭제하려는 게시글을 다시 확인해주세요.");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryEntity>> GetCategory(){

        List<CategoryEntity> category = categoryService.getCategoryList();

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

}