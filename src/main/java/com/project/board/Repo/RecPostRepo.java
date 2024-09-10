package com.project.board.Repo;

import com.project.board.Entity.RecPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecPostRepo extends JpaRepository<RecPostEntity, Long> {
    @Query(value="SELECT * FROM post_rec rec_p WHERE rec_p.rec_post_pid = :post_pid AND rec_p.rec_user_id = :user_id", nativeQuery = true)
    RecPostEntity findAlreadyRec(@Param("post_pid") Long post_pid, @Param("user_id") String user_id);
}