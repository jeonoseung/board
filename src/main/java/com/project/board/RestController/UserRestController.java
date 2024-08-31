package com.project.board.RestController;


import com.project.board.DTO.Request.CreateUserRequest;
import com.project.board.DTO.Request.LoginUserRequest;
import com.project.board.DTO.Response.ResponseAccessToken;
import com.project.board.DTO.Response.ResponseLoginToken;
import com.project.board.DTO.Response.ResponseUserInfo;
import com.project.board.Enum.TokenType;
import com.project.board.Service.UserService;
import com.project.board.Utils.JwtToken;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final JwtToken jwtToken;

    @PostMapping("/user/signup")
    public ResponseEntity<Void> CreateUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        userService.CreateUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/user/login")
    public ResponseEntity<ResponseLoginToken> LoginUser(@Valid @RequestBody LoginUserRequest loginUserRequest){
        String user_id = userService.CheckLoginUser(loginUserRequest);

        String access_token = jwtToken.createToken(user_id, TokenType.ACCESS);
        String refresh_token = jwtToken.createToken(user_id, TokenType.REFRESH);

        ResponseLoginToken body = new ResponseLoginToken();
        body.setAccessToken(access_token);
        body.setRefreshToken(refresh_token);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
    @PostMapping("/user/info")
    public ResponseEntity<ResponseUserInfo> CheckUser(@CookieValue(value = "access_token") String cookieValue){
        jwtToken.validateToken(cookieValue);
        String user_id = jwtToken.getUserId(cookieValue);
        ResponseUserInfo responseUserInfo = userService.GetUserInfo(user_id);

        return ResponseEntity.status(HttpStatus.OK).body(responseUserInfo);
    }

    @PostMapping("/user/refresh")
    public ResponseEntity<ResponseAccessToken> RefreshUser(@CookieValue(value = "refresh_token") String cookieValue){
        jwtToken.validateToken(cookieValue);
        String user_id = jwtToken.getUserId(cookieValue);

        String access_token = jwtToken.createToken(user_id, TokenType.ACCESS);

        ResponseAccessToken body = new ResponseAccessToken();
        body.setAccessToken(access_token);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/user/check/id")
    public void CheckUserId(@Valid @NotBlank(message = "아이디를 입력해 주세요.") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문자와 숫자만 입력 가능합니다.") @RequestParam(value="user_id",required = false) String user_id){
        if(userService.UserIdReCheck(user_id)){
            throw new IllegalArgumentException("이미 가입되어 있는 아이디입니다.");
        }
    }
    @GetMapping("/user/check/nickname")
    public void CheckUserNickname(@Valid @NotBlank(message = "닉네임 입력해 주세요.") @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "영문자와 숫자만 입력 가능합니다.") @RequestParam(value="user_nickname",required = false) String user_nickname){
        if(userService.UserNicknameReCheck(user_nickname)){
            throw new IllegalArgumentException("이미 가입되어 있는 닉네임입니다.");
        }
    }
}