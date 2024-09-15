package com.project.board.Service;

import java.util.List;
import java.util.regex.Pattern;

import com.project.board.DTO.QuerInterface.PostListView;
import com.project.board.DTO.Request.CreatePostRequest;
import com.project.board.Entity.UserEntity;
import com.project.board.Enum.PostState;
import com.project.board.ExceptionHandler.UnauthorizedException;
import com.project.board.Repo.UserRepo;
import com.project.board.Utils.JwtToken;
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
    private final JwtToken jwtToken;

    public List<PostListView> GetPostList(String page, String user_id, String search, Long category){
        String regex = "^\\d+$";
        if(page != null){
            boolean isNumber = Pattern.matches(regex,page);
            if(isNumber){
                int c = Integer.parseInt(page);
                c--;
                int length = 10;
                if(c < 0){
                    c = 0;
                }
                return postRepo.getPostList(c*length,PostState.ACTIVE.name(),user_id,search,category);
            }
            else {
                return postRepo.getPostList(0,PostState.ACTIVE.name(),user_id,search,category);
            }
        }
        else {
            return postRepo.getPostList(0,PostState.ACTIVE.name(),user_id,search,category);
        }
    }

    public PostListView getPostDetail(Long pid, String user_id){
        postRepo.incrementPostViewCount(pid);
        PostListView post = postRepo.getPost(pid,PostState.ACTIVE.name(),user_id);
        return post;
    }

    public Long GetPostLength (String search, Long category){
        return postRepo.getPostListCount(PostState.ACTIVE.name(),search,category);
    }

    public void CreatePost(CreatePostRequest createPostRequest,String user_id) throws IllegalAccessException {

        CategoryEntity category = categoryRepo.findById(createPostRequest.GetPostCategory())
        .orElseThrow(() -> new IllegalArgumentException("카테고리가 올바르지 않습니다."));
        UserEntity user = userRepo.findUserId(user_id);
        PostEntity post = new PostEntity();
        post.setTitle(createPostRequest.GetPostTitle());
        post.setContent(createPostRequest.GetPostContent());
        post.setCategory(category);
        post.setState(PostState.ACTIVE);
//        post.setPostKeyword(createPostRequest.GetPostKeyword());
        post.setAuthor(user);
        postRepo.save(post);
    }
    public void deletePost(Long pid, String token){
        if(jwtToken.validateToken(token)){
            String user_id = jwtToken.getUserId(token);
            postRepo.findById(pid).orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다."));
            PostEntity post = postRepo.findPost(pid,user_id);
            if(post == null){
                throw new UnauthorizedException("삭제 권한이 없습니다.");
            }
            postRepo.updatePostState(pid,PostState.DELETE.name());
        }
    }

    public void updatePost(CreatePostRequest updatePostRequest,String user_id,Long post_pid){
        CategoryEntity category = categoryRepo.findById(updatePostRequest.GetPostCategory())
        .orElseThrow(() -> new IllegalArgumentException("카테고리가 올바르지 않습니다."));
        UserEntity user = userRepo.findUserId(user_id);
        if(user == null){
            throw new UnauthorizedException("해당 게시글을 수정할 권한이 없습니다.");
        }
        postRepo.updatePost(
                updatePostRequest.GetPostTitle(),
                updatePostRequest.GetPostContent(),
                updatePostRequest.GetPostCategory(),
                post_pid
        );
    }
}
