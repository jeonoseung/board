package com.project.board.Repo;


import com.project.board.DTO.QuerInterface.PostListView;
import com.project.board.Enum.PostState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.board.Entity.PostEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<PostEntity,Long> {
    
    @Query(value = "SELECT * FROM post ORDER BY post_pid DESC LIMIT 10",nativeQuery = true)
    List<PostEntity> postListRecent();

    @Query(value = "SELECT p.post_pid, p.post_title, p.post_content, p.post_create_date, u.user_nickname, c.category_name, p.post_view, " +
                   "COUNT(cm.comment_pid) AS comment_count, COUNT(rec.rec_pid) AS rec_count, IF(p.post_author = :user_id,true,false) AS post_mine, " +
                   "IF((SELECT COUNT(*) FROM board.post_rec WHERE rec_post_pid = p.post_pid AND rec_user_id = :user_id) >= 1,true,false) AS post_recommend " +
                   "FROM post p " +
                   "INNER JOIN user u ON p.post_author = u.user_id " +
                   "INNER JOIN category c ON p.post_category = c.category_pid " +
                   "LEFT JOIN comment cm ON p.post_pid = cm.comment_post " +
                   "LEFT JOIN post_rec rec ON p.post_pid = rec.rec_post_pid " +
                   "WHERE p.post_state = :state " +
                   "GROUP BY p.post_pid " +
                   "ORDER BY p.post_pid DESC " +
                   "LIMIT 10 OFFSET :offset",
           nativeQuery = true)
    List<PostListView> getPostList(@Param("offset") int offset, @Param("state") String state, @Param("user_id") String user_id);

    @Query(value = "SELECT p.post_pid, p.post_title, p.post_content, p.post_create_date, u.user_nickname, c.category_name, p.post_view, " +
                   "COUNT(cm.comment_pid) AS comment_count, COUNT(rec.rec_pid) AS rec_count, IF(p.post_author = :user_id,true,false) AS post_mine, " +
                   "IF((SELECT COUNT(*) FROM board.post_rec WHERE rec_post_pid = p.post_pid AND rec_user_id = :user_id) >= 1,true,false) AS post_recommend " +
                   "FROM post p " +
                   "INNER JOIN user u ON p.post_author = u.user_id " +
                   "INNER JOIN category c ON p.post_category = c.category_pid " +
                   "LEFT JOIN comment cm ON p.post_pid = cm.comment_post " +
                   "LEFT JOIN post_rec rec ON p.post_pid = rec.rec_post_pid " +
                   "WHERE p.post_pid = :pid AND p.post_state = :state " +
                   "GROUP BY p.post_pid " +
                   "ORDER BY p.post_pid DESC ",
           nativeQuery = true)
    PostListView getPost(@Param("pid") Long pid, @Param("state") String state, @Param("user_id") String user_id);

    @Transactional
    @Query(value = "UPDATE post SET post_view = post_view + 1 WHERE post_pid = :pid", nativeQuery = true)
    @Modifying
    void incrementPostViewCount(@Param("pid") Long pid);

    @Query(value = "SELECT * FROM post p WHERE p.post_pid = :post_pid AND p.post_author = :user_id",nativeQuery = true)
    PostEntity findPost(@Param("post_pid") Long post_pid, @Param("user_id") String user_id);

    @Transactional
    @Query(value = "UPDATE post SET post_state = :state WHERE post_pid = :post_pid", nativeQuery = true)
    @Modifying
    void updatePostState(@Param("post_pid") Long post_pid, @Param("state") String state);
}
