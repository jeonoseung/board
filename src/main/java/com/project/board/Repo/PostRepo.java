package com.project.board.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.board.Entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<PostEntity,Long> {
    
    @Query(
            value = "SELECT * FROM post ORDER BY post_pid DESC LIMIT :start,:end",
            nativeQuery = true
    )
    List<PostEntity> postList(@Param("start") int start, @Param("end") int end);

}
