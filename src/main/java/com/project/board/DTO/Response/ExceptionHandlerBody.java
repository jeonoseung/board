package com.project.board.DTO.Response;

public class ExceptionHandlerBody {
    public String errorMessage = "에러가 발생했습니다.";
    public void SetErrorMessage(String message){
        this.errorMessage = message;
    }
}