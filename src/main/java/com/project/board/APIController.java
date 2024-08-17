package com.project.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class APIController {

    @GetMapping("/post/wirte")
    public void postMethodName(){
        // postService.InsertPost();
    }
}

