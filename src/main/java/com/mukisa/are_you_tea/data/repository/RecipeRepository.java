package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

    @Query("SELECT DISTINCT r.recipeType FROM RecipeEntity r")
    List<String> findDistinctByRecipeType();

    List<RecipeEntity> findByRecipeType(String recipeType);
}
