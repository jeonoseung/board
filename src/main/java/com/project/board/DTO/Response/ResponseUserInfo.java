package com.project.board.DTO.Response;

import java.time.LocalDateTime;

public class ResponseUserInfo {
    public String user_id;
    public String user_nickname;
    public LocalDateTime user_create_date;
    
    
    public void setUserId(String id){
        this.user_id = id;
    }
    public void setUserNickname(String nickname){
        this.user_nickname = nickname;
    }
    public void setUserInfo(String user_id, String user_nickname, LocalDateTime user_create_date){
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_create_date = user_create_date;
    }
    
}