package com.project.board.DTO.Response;

public class ResponseLoginToken {
    public String access_token;
    public String refresh_token;
    
    public void setAccessToken(String token){
        this.access_token = token;
    }
    public void setRefreshToken(String token){
        this.refresh_token = token;
    }
}