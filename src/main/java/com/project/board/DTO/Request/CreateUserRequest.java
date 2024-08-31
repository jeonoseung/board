package com.project.board.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    @NotBlank(message = "아이디를 입력해 주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문자와 숫자만 입력 가능합니다.")
    @Size(min=6,max=20,message = "아이디는 최소 6자 ~ 최대 20자 입력이 필요합니다.")
    private String user_id;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+={}\\[\\]:;\"'|\\\\<>,.?/~`-]*$", message = "영문자와 숫자, 공백을 제외한 특수 문자만 입력 가능합니다.")
    @Size(min=8,max=30,message = "비밀번는 최소 8자 ~ 최대 30자 입력이 필요합니다.")
    private String user_pass;
    @NotBlank(message = "닉네임 입력해 주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "영문자,한글,숫자만 입력 가능합니다.")
    @Size(min=4,max=10,message = "닉네임 최소 4자 ~ 최대 10자 입력이 필요합니다.")
    private String user_nickname;
    
    public String GetUserId(){
        return user_id;
    }
    public String GetUserPass(){
        return user_pass;
    }
    public String GetUserNickname(){
        return user_nickname;
    }
}