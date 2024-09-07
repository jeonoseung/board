package com.project.board.Service;

import com.project.board.DTO.Request.CreateCommentRequest;
import com.project.board.Entity.CommentEntity;
import com.project.board.Entity.PostEntity;
import com.project.board.Entity.UserEntity;
import com.project.board.ExceptionHandler.UnauthorizedException;
import com.project.board.Repo.CommentRepo;
import com.project.board.Repo.PostRepo;
import com.project.board.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    public void createComment(CreateCommentRequest createCommentRequest,String user_id){
        PostEntity post = postRepo.findById(createCommentRequest.getPostPid())
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글에 댓글을 작성할 수 없습니다."));
        UserEntity user = userRepo.findUserId(user_id);
        if(user == null){
            throw new UnauthorizedException("유저 정보를 조회할 수 없습니다.");
        }
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(createCommentRequest.getCommentContent());
        commentEntity.setCommentAuthor(user);
        commentEntity.setCommentPost(post);
        if(createCommentRequest.getTargetId() != null){
            CommentEntity comment = commentRepo.findCommentId(createCommentRequest.getTargetId());
            if(comment == null){
                throw new IllegalArgumentException("해당 댓글에 작성할 수 없습니다.");
            }
            commentEntity.setCommentTarget(comment.getComment_pid());
        }
        commentRepo.save(commentEntity);
    }
    
    public List<CommentEntity> getPostCommentList(Long post_pid){
        PostEntity post = postRepo.findById(post_pid)
        .orElseThrow(() -> new IllegalArgumentException("게시글을 조회할 수 없습니다.."));
        return commentRepo.findPostCommentList(post);
    }
}