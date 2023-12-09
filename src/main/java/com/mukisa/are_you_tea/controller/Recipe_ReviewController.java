package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.entity.DictionaryEntity;
import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.data.entity.Recipe_ReviewEntity;
import com.mukisa.are_you_tea.service.CommunityService;
import com.mukisa.are_you_tea.service.Recipe_ReviewService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class Recipe_ReviewController {

    @Autowired
    private Recipe_ReviewService recipeReviewService;

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionCheckService sessionCheckService;

    @RequestMapping("/recipe_review")
    public String goRecipeReview(@RequestParam(name = "recipeno", required = false) Integer recipeno, Model model) {
        sessionCheckService.sessionCheck(model, httpSession);

        try {


            if (recipeno != null) {
                List<Recipe_ReviewEntity> comments = recipeReviewService.getRecipeReviewByRecipeId(recipeno);
                if (recipeno != null) {
                    model.addAttribute("recipeDetailData", recipeno);
                } else {
                    // 해당 recipeno에 대한 레코드가 없을 경우에 대한 처리

                }
            } else {
                // recipeno가 제공되지 않은 경우에 대한 처리

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "recipeDetail";
    }



}

