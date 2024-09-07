package com.project.board.Service;

import com.project.board.Entity.CategoryEntity;
import com.project.board.Repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;
    
    public List<CategoryEntity> getCategoryList() {
        return categoryRepo.findAll();
    }
}