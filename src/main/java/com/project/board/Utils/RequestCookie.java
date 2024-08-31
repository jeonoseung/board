package com.project.board.Utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestCookie {
    
    private final HttpServletRequest request;
    
    @Autowired
    public RequestCookie(HttpServletRequest req) {
        this.request = req;
    }
    
    public boolean checkCookie(String cookieName){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String token = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    token = cookie.getValue();
                }
            }
            return token != null;
        }
        else {
            return false;
        }
    }
}