package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    //레시지 목록
    @RequestMapping("/recipe")
    public String gorecipeList(Model model) {
        try {
            List<RecipeEntity> dataset = recipeService.dataLoad();

            if (!dataset.isEmpty()) {
                // 모든 데이터를 모델에 추가
                for (int i = 0; i < dataset.size(); i++) {
                    model.addAttribute("recipeData", dataset);

                }

            } else {
                System.out.println("데이터셋이 비어 있습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "recipe";
    }


}
