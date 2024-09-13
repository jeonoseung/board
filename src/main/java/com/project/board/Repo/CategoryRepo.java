package com.project.board.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.board.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends JpaRepository<CategoryEntity,Long> {
    @Query(value="SELECT * FROM category c WHERE c.category_pid = :category_pid",nativeQuery = true)
    CategoryEntity findCategoryPid(@Param("category_pid") Long category_pid);
}
