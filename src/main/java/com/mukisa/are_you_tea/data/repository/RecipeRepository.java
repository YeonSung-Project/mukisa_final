package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

    @Query("SELECT DISTINCT r.recipeType FROM RecipeEntity r")
    List<String> findDistinctByRecipeType();

    List<RecipeEntity> findByRecipeType(String recipeType);

    // 특정 레시피 타입에 대한 페이징된 목록 가져오기
    Page<RecipeEntity> findByRecipeType(String recipeType, Pageable pageable);

    // 모든 레시피에 대한 페이징된 목록 가져오기
    Page<RecipeEntity> findAll(Pageable pageable);
}
