package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.data.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    //목록 조회
    public List<RecipeEntity> dataLoad(){
        try {
            return recipeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ID로 레시피 조회
    public RecipeEntity findRecipeId(int recipeno) {
        Optional<RecipeEntity> recipeOptional = recipeRepository.findById(recipeno);
        return recipeOptional.orElse(null);
    }

    // 레시피 생성
    public RecipeEntity createRecipe(RecipeEntity recipe) {
        return recipeRepository.save(recipe);
    }

    // 레시피 삭제
    public void deleteRecipe(int recipeno) {

        recipeRepository.deleteById(recipeno);
    }

}
