package com.project.board.Utils;

import com.project.board.DTO.Response.ResponseUserInfo;
import com.project.board.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


@Component
public class ModelAndViewBuilder {
    private final ModelAndView mv;
    private final JwtToken jwtToken;
    private final UserService userService;
    
    @Autowired
    public ModelAndViewBuilder(JwtToken jwtToken, UserService userService) {
        this.mv = new ModelAndView();
        this.jwtToken = jwtToken;
        this.userService = userService;
    }
    
    public ModelAndView redirect(String url){
        mv.setViewName("redirect:"+url);
        return this.mv;
    }

    public ModelAndView loginRedirect(String url){
        String encode = URLEncoder.encode(url, StandardCharsets.UTF_8);
        mv.setViewName("redirect:/login?redirect_url="+encode);
        return this.mv;
    }

    public ModelAndViewBuilder init(HttpServletRequest req){
        this.mv.setViewName("layout");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String token = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("access_token")) {
                    token = cookie.getValue();
                }
            }
            if(token != null){
                String user_id = jwtToken.getUserId(token);
                ResponseUserInfo userInfo = userService.GetUserInfo(user_id);
                mv.addObject("user",userInfo);
            }
            else {
                mv.addObject("user",null);
            }
        }
        return this;
    }
    
    public ModelAndViewBuilder viewContent(String route){
        mv.addObject("url",route);
        return this;
    }
    
    public ModelAndView build(){
        return this.mv;
    }
    
}