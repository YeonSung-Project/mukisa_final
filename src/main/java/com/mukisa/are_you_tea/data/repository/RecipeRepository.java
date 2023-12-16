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


    // 레시피 타입으로 레시피 조회
    List<RecipeEntity> findByRecipeType(String recipeType);



}
