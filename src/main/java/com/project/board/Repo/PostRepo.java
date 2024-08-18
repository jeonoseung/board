package com.project.board.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.board.Entity.PostEntity;

public interface PostRepo extends JpaRepository<PostEntity,Long> {
    
}
