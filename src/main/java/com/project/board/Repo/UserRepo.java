package com.project.board.Repo;

import com.project.board.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserEntity,Long> {
    
}