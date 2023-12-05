package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.Recipe_ReviewEntity;
import com.mukisa.are_you_tea.data.repository.Recipe_ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Recipe_ReviewService {

    @Autowired
    private Recipe_ReviewRepository recipeReviewRepository;

    //레시피 댓글 리스트 조회
    public List<Recipe_ReviewEntity> dataLoad(){
        try {
            return recipeReviewRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // 레시피 리뷰 글 작성 ,수정
    public void recipeReviewWrite(Recipe_ReviewEntity recipe_reviewEntity) throws Exception{


        recipeReviewRepository.save(recipe_reviewEntity);
    }

    // 커뮤니티 특정 글 삭제
    public void recipeReviewDelete(Integer Recipe_ReviewNo) {
        recipeReviewRepository.deleteById(Recipe_ReviewNo);
    }



}
