package com.project.board.Service;

import java.util.List;

import com.project.board.DTO.Request.CreatePostRequest;
import com.project.board.Entity.UserEntity;
import com.project.board.Enum.PostState;
import com.project.board.Repo.UserRepo;
import org.springframework.stereotype.Service;

import com.project.board.Entity.CategoryEntity;
import com.project.board.Entity.PostEntity;
import com.project.board.Repo.CategoryRepo;
import com.project.board.Repo.PostRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;

    public List<PostEntity> GetPostList(int page){
        int start = page;
        int length = 10;
        if(start < 1){
            start = 1;
        }
        else {
            start -= 1;
        }
        int end = page * length;
        List<PostEntity> posts = postRepo.postList(start,end);

        return posts;
    }

    public Long GetPostLength (){
        return postRepo.count();
    }

    public void CreatePost(CreatePostRequest createPostRequest,String user_id) throws IllegalAccessException {

        CategoryEntity category = categoryRepo.findById(createPostRequest.GetPostCategory())
        .orElseThrow(() -> new IllegalArgumentException("카테고리가 올바르지 않습니다."));
        UserEntity user = userRepo.findUserId(user_id);
        PostEntity post = new PostEntity();
        post.setTitle(createPostRequest.GetPostTitle());
        post.setContent(createPostRequest.GetPostContent());
        post.setCategory(category);
        post.setPost_state(PostState.ACTIVE);
        post.setPost_keyword(createPostRequest.GetPostKeyword());
        post.setAuthor(user);
        postRepo.save(post);
    }
}
