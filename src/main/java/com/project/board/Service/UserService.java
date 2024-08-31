package com.project.board.Service;



import com.project.board.DTO.QuerInterface.UserLoginCheck;
import com.project.board.DTO.Request.CreateUserRequest;
import com.project.board.DTO.Request.LoginUserRequest;
import com.project.board.DTO.Response.ResponseUserInfo;

import com.project.board.Entity.UserEntity;
import com.project.board.Repo.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    //회원 생성
    public void CreateUser(CreateUserRequest createUserRequest){
        UserEntity user = new UserEntity();
        user.setUserId(createUserRequest.GetUserId());
        user.setUserPass(createUserRequest.GetUserPass());
        user.setUserNickname(createUserRequest.GetUserNickname());
        userRepo.save(user);
    }

    //로그인하는 유저가 존재하고 비밀번호가 일치한지 확인
    public String CheckLoginUser(LoginUserRequest loginUserRequest){
        String request_id = loginUserRequest.GetUserId();
        String request_pass = loginUserRequest.GetUserPass();
        UserLoginCheck user = userRepo.userInfo(request_id);
        if(user == null){
            throw new IllegalArgumentException("아이디 또는 비밀번호를 다시 확인해 주세요.");
        }
        String user_id = user.getUser_id();
        String user_pass = user.getUser_pass();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean pass_check = passwordEncoder.matches(request_pass,user_pass);
        if(!pass_check){
            throw new IllegalArgumentException("아이디 또는 비밀번호를 다시 확인해 주세요.");
        }
        return user_id;
    }

    //사용자 정보 조회
    public ResponseUserInfo GetUserInfo(String user_id){
        UserLoginCheck user = userRepo.userInfo(user_id);
        ResponseUserInfo responseUserInfo = new ResponseUserInfo();
        responseUserInfo.setUserInfo(user.getUser_id(),user.getUser_nickname(),user.getUser_create_date());
        return responseUserInfo;
    }

    public boolean UserIdReCheck(String user_id){
         UserEntity user = userRepo.findUserId(user_id);
        return user != null;
    }

    public boolean UserNicknameReCheck(String user_nickname){
        UserEntity user = userRepo.findUserNickname(user_nickname);
        return user != null;
    }
}