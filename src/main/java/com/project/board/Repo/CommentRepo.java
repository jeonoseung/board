package com.project.board.Repo;


import com.project.board.Entity.CommentEntity;
import com.project.board.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity,Long> {
    
    @Query(value="SELECT cm FROM CommentEntity cm WHERE cm.comment_pid = :pid")
    CommentEntity findCommentId(@Param("pid") Long pid);
    
    @Query(value="SELECT cm FROM CommentEntity cm WHERE cm.comment_post = :post_pid")
    List<CommentEntity> findPostCommentList(@Param("post_pid") PostEntity post_pid);
}