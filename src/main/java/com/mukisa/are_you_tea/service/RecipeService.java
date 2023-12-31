package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.data.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public Page<RecipeEntity> recipeList(Pageable pageable) {

        return recipeRepository.findAll(pageable);
    }


    //목록 조회
    public List<RecipeEntity> dataLoad(){
        try {
            return recipeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 레시피 타입으로 레시피 조회
    public List<RecipeEntity> getRecipesByType(String recipeType) {
        return recipeRepository.findByRecipeType(recipeType);
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

    //레시피 타입 가져오기
    public List<String> getDistinctRecipeTypes() {
        return recipeRepository.findDistinctByRecipeType();
    }
    //타입 검색
    public List<RecipeEntity> findByRecipeType(String recipeType) {
        return recipeRepository.findByRecipeType(recipeType);
    }


    public Page<RecipeEntity> getRecipeListWithPaging(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }
}
