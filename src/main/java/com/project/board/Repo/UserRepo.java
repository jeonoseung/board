package com.project.board.Repo;

import com.project.board.DTO.UserLoginCheck;
import com.project.board.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface UserRepo extends JpaRepository<UserEntity,Long> {

    @Query(value = "SELECT " +
                   "u.user_id AS user_id, " +
                   "u.user_pass AS user_pass, " +
                   "u.user_nickname AS user_nickname, " +
                   "u.user_create_date AS user_create_date " +
                   "FROM UserEntity u " +
                   "WHERE u.user_id=:user_id")
    UserLoginCheck userInfo(@Param("user_id") String user_id);
    
}