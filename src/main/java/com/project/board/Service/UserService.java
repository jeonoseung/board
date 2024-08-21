package com.project.board.Service;

import com.project.board.DTO.CreateUserRequest;
import com.project.board.Entity.UserEntity;
import com.project.board.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    
    public void CreateUser(CreateUserRequest createUserRequest){
        UserEntity user = new UserEntity();
        user.setUserId(createUserRequest.GetUserId());
        user.setUserPass(createUserRequest.GetUserPass());
        user.setUserNickname(createUserRequest.GetUserNickname());
        userRepo.save(user);
    }
}