package com.project.board;

import com.project.board.Entity.PostEntity;
import com.project.board.Service.PostService;
import com.project.board.Service.UserService;
import com.project.board.Utils.JwtToken;
import com.project.board.Utils.ModelAndViewBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.jar.JarEntry;
import java.util.regex.Pattern;

@Controller
@RequiredArgsConstructor
public class PageController {
    
    private final PostService postService;
    private final UserService userService;
    private final JwtToken jwtToken;

    @RequestMapping("/")
    public ModelAndView ViewMainPage(HttpServletRequest req){
        String page = req.getParameter("page");
        String regex = "^\\d+$";
        int _page = 1;
        if(page != null){
            boolean isNumber = Pattern.matches(regex,page);
            if(isNumber){
                _page = Integer.parseInt(page);
            }
        }
        List<PostEntity> posts = postService.GetPostList(_page);
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("index.jsp").build();

        mv.addObject("post",posts);
        mv.addObject("post_length",postService.GetPostLength());

        return mv;
    }

    @RequestMapping("/write")
    public ModelAndView WritePage(HttpServletRequest req){
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("write/index.jsp").build();
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView LoginPage(HttpServletRequest req){
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("login/index.jsp").build();
        return mv;
    }
}
