package com.project.board;

import com.project.board.DTO.QuerInterface.PostListView;
import com.project.board.Entity.CategoryEntity;
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
import org.springframework.web.bind.annotation.CookieValue;
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
    public ModelAndView ViewMainPage(
            HttpServletRequest req,
            @CookieValue(value="access_token",required = false) String access
    ){
        String page = req.getParameter("page");
        String id = "";
        if(jwtToken.checkValidateToken(access)){
            id = jwtToken.getUserId(access);
        }
        String search = req.getParameter("search");
        String category = req.getParameter("category");
        String searchValue = "";
        Long categoryValue = null;

        if(search != null){
            searchValue = search.trim();
        }
        if(category != null){
            String regex = "^\\d+$";
            boolean isNumber = Pattern.matches(regex,category);
            System.out.println("111111");
            if(isNumber){
                Long cate = Long.parseLong(category);
                categoryValue = cate;
            }
        }
        List<PostListView> posts = postService.GetPostList(page,id,searchValue,categoryValue);
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("index.jsp").build();
        mv.addObject("post",posts);
        mv.addObject("post_length",postService.GetPostLength(searchValue,categoryValue));
        mv.addObject("category",categoryService.getCategoryList());

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
    public ModelAndView PostViewPage(
            HttpServletRequest req,
            @PathVariable(value = "post_pid",required = false) String post_pid,
            @CookieValue(value="access_token",required = false) String access
    ){
        if(post_pid == null){
            return new ModelAndViewBuilder(jwtToken,userService).redirect("/");
        }
        String regex = "^\\d+$";
        boolean isNumber = Pattern.matches(regex,post_pid);
        if(!isNumber){
            return new ModelAndViewBuilder(jwtToken,userService).redirect("/");
        }
        String id = "";
        if(jwtToken.checkValidateToken(access)){
            id = jwtToken.getUserId(access);
        }
        Long pid = Long.parseLong(post_pid);
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("post/index.jsp").build();
        PostListView postDetail = postService.getPostDetail(pid, id);
        mv.addObject("title",postDetail.getPost_title());
        mv.addObject("post",postDetail);
        mv.addObject("comment",commentService.getPostCommentList(pid, id));
        return mv;
    }
    @RequestMapping("/update/{post_pid}")
    public ModelAndView PostUpdatePage(
            HttpServletRequest req,
            @PathVariable(value = "post_pid",required = false) String post_pid,
            @CookieValue(value="access_token",required = false) String access
    ){
        if(post_pid == null){
            return new ModelAndViewBuilder(jwtToken,userService).redirect("/");
        }
        String regex = "^\\d+$";
        boolean isNumber = Pattern.matches(regex,post_pid);
        if(!isNumber){
            return new ModelAndViewBuilder(jwtToken,userService).redirect("/");
        }
        String id = "";
        if(jwtToken.checkValidateToken(access)){
            id = jwtToken.getUserId(access);
        }
        else {
            return new ModelAndViewBuilder(jwtToken,userService).redirect("/");
        }

        Long pid = Long.parseLong(post_pid);
        ModelAndView mv = new ModelAndViewBuilder(jwtToken,userService).init(req).viewContent("update/index.jsp").build();
        PostListView postDetail = postService.getPostDetail(pid, id);
        mv.addObject("title","게시글 수정");
        mv.addObject("post",postDetail);
        mv.addObject("category",categoryService.getCategoryList());
        return mv;
    }
}
