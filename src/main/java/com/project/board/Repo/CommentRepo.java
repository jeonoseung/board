package com.project.board.Repo;


import com.project.board.DTO.QuerInterface.CommentList;
import com.project.board.Entity.CommentEntity;
import com.project.board.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity,Long> {
    
    @Query(value="SELECT cm FROM CommentEntity cm WHERE cm.comment_pid = :pid")
    CommentEntity findCommentId(@Param("pid") Long pid);

    @Query(value="SELECT cm.comment_pid, cm.comment_content, cm.comment_create_date, cm.comment_target, u.user_nickname, " +
                 "IF(cm.comment_author = :user_id, true, false) AS comment_mine " +
                 "FROM comment cm " +
                 "INNER JOIN user u ON cm.comment_author = u.user_id " +
                 "WHERE cm.comment_post = :post_pid ",
    nativeQuery = true)
    List<CommentList> findPostCommentList(@Param("post_pid") Long post_pid, @Param("user_id") String user_id);
    

    @Query(
            value="SELECT * " +
                 "FROM comment cm " +
                 "WHERE cm.comment_pid = :comment_pid " +
                 "AND cm.comment_author = :user_id",
            nativeQuery = true
    )
    CommentEntity findAuthor(@Param("comment_pid") Long comment_pid, @Param("user_id") String user_id);
}