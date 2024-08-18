package com.project.board.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.board.Entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity,Long> {
    
}
