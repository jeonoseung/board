package com.project.board.Service;

import com.project.board.Entity.PostEntity;
import com.project.board.Entity.RecPostEntity;
import com.project.board.Entity.UserEntity;
import com.project.board.Repo.PostRepo;
import com.project.board.Repo.RecPostRepo;
import com.project.board.Repo.UserRepo;
import com.project.board.Utils.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final RecPostRepo recPostRepo;
    private final JwtToken jwtToken;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public void RecommendPost(Long post_pid, String token){
        jwtToken.validateToken(token);
        PostEntity post = postRepo.findById(post_pid).orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        String user_id = jwtToken.getUserId(token);
        UserEntity user = userRepo.findUserId(user_id);
        if(user == null){
            throw new IllegalArgumentException("회원 정보를 조회할 수 없습니다.");
        }
        RecPostEntity check = recPostRepo.findAlreadyRec(post_pid,user_id);
        if(check != null){
            throw new IllegalArgumentException("이미 추천 중인 게시글입니다.");
        }
        RecPostEntity recPostEntity = new RecPostEntity();
        recPostEntity.setPostPid(post);
        recPostEntity.setUserId(user);
        recPostRepo.save(recPostEntity);
    }

}