package com.project.board;

import com.project.board.Entity.PostEntity;
import com.project.board.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {
    
    private final PostService postService;

    @RequestMapping("/")
    public ModelAndView ViewMainPage(ModelAndView mv){
        List<PostEntity> posts = postService.GetPostList();

        mv.setViewName("layout");
        mv.addObject("url","index.jsp");
        mv.addObject("post",posts);

        return mv;
    }

    
}
