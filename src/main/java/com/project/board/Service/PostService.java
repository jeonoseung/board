package com.project.board.Service;

import java.util.List;

import com.project.board.Enum.PostState;
import org.springframework.stereotype.Service;

import com.project.board.DTO.CreatePostRequest;
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

    public List<PostEntity> GetPostList(){
        List<PostEntity> posts = postRepo.findAll();
        return posts;
    }

    public void CreatePost(CreatePostRequest createPostRequest){

        CategoryEntity category = categoryRepo.findById(createPostRequest.GetPostCategory())
        .orElseThrow(() -> new IllegalArgumentException("카테고리가 올바르지 않습니다."));
        PostEntity post = new PostEntity();
        post.setTitle(createPostRequest.GetPostTitle());
        post.setContent(createPostRequest.GetPostContent());
        post.setCategory(category);
        post.setPost_state(PostState.ACTIVE);
        post.setPost_keyword(createPostRequest.GetPostKeyword());
        postRepo.save(post);
    }
}
