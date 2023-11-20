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

    public List<RecipeEntity> dataLoad(){
        try {
            return recipeRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
    public RecipeEntity findById(int recipeno) {
        Optional<RecipeEntity> recipeOptional = recipeRepository.findById(recipeno);
        return recipeOptional.orElse(null);
    }

}
