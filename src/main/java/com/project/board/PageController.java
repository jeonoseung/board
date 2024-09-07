package com.project.board;

import com.project.board.DTO.QuerInterface.PostListView;
import com.project.board.Entity.PostEntity;
import com.project.board.Service.CategoryService;
import com.project.board.Service.CommentService;
import com.project.board.Service.PostService;
import com.project.board.Service.UserService;
import com.project.board.Utils.JwtToken;
import com.project.board.Utils.ModelAndViewBuilder;
import com.project.board.Utils.RequestCookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.regex.Pattern;

@Controller
@RequiredArgsConstructor
public class PageController {
    
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    private final JwtToken jwtToken;
    private final CategoryService categoryService;

    @RequestMapping("/")
    public ModelAndView ViewMainPage(HttpServletRequest req){
        String page = req.getParameter("page");
        List<PostListView> posts = postService.GetPostList(page);
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("index.jsp").build();

        mv.addObject("post",posts);
        mv.addObject("post_length",postService.GetPostLength());

        return mv;
    }

    @RequestMapping("/write")
    public ModelAndView WritePage(HttpServletRequest req) throws UnsupportedEncodingException {
        RequestCookie requestCookie = new RequestCookie(req);
        ModelAndView mv;
        if(requestCookie.checkCookie("access_token")){
            mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("write/index.jsp").build();
            mv.addObject("category",categoryService.getCategoryList());
        }
        else {
            return new ModelAndViewBuilder(jwtToken,userService).loginRedirect(req.getRequestURI());
        }
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView LoginPage(HttpServletRequest req){

        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("login/index.jsp").build();
        return mv;
    }

    @RequestMapping("/signup")
    public ModelAndView SinghUpPage(HttpServletRequest req){
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("signup/index.jsp").build();
        return mv;
    }

    @RequestMapping("/post/{post_pid}")
    public ModelAndView PostViewPage(HttpServletRequest req, @PathVariable(value = "post_pid",required = false) String post_pid){
        if(post_pid == null){
            return new ModelAndViewBuilder(jwtToken,userService).redirect("/");
        }
        String regex = "^\\d+$";
        boolean isNumber = Pattern.matches(regex,post_pid);
        if(!isNumber){
            return new ModelAndViewBuilder(jwtToken,userService).redirect("/");
        }
        Long pid = Long.parseLong(post_pid);
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("post/index.jsp").build();
        mv.addObject("post",postService.getPostDetail(pid));
        mv.addObject("comment",commentService.getPostCommentList(pid));
        return mv;
    }
}
