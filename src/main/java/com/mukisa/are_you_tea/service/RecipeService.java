package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.data.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    // 레시피 수정
    public RecipeEntity updateRecipe(int recipeno, RecipeEntity updatedRecipe) {
        Optional<RecipeEntity> existingRecipeOptional = recipeRepository.findById(recipeno);

        if (existingRecipeOptional.isPresent()) {
            RecipeEntity existingRecipe = existingRecipeOptional.get();

            // Update the fields you want to change in the existingRecipe using setters or other methods
            // For example: existingRecipe.setName(updatedRecipe.getName());
            //              existingRecipe.setIngredients(updatedRecipe.getIngredients());

            return recipeRepository.save(existingRecipe);
        } else {
            return null; // Handle the case where the recipe with given ID doesn't exist
        }
    }

    // 레시피 삭제
    public void deleteRecipe(int recipeno) {
        recipeRepository.deleteById(recipeno);
    }

}
