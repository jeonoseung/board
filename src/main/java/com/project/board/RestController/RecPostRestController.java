package com.project.board.RestController;

import com.project.board.Service.RecommendService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecPostRestController {

    private final RecommendService recommendService;

    @PostMapping("/post/rec/{post_pid}")
    public ResponseEntity<Void> RecPost(
            @PathVariable(value="post_pid",required = false)
            String post_pid,
            @NotBlank(message = "로그인 후 이용할 수 있습니다.")
            @CookieValue(value = "access_token",required = false)
            String access
    ){
        Long pid;
        try {
            pid = Long.parseLong(post_pid);
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("게시글 PID가 올바르지 않습니다.");
        }

        recommendService.RecommendPost(pid,access);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}