package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.service.RecipeService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionCheckService sessionCheckService;

    //레시지 목록
    @RequestMapping("/recipe")
    public String gorecipeList(Model model) {
        try {
            List<RecipeEntity> dataset = recipeService.dataLoad();
            sessionCheckService.sessionCheck(model, httpSession);
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
    //상세 레시피 보기
    @RequestMapping("/recipeDetail")
    public String gorecipeDetail(@RequestParam(name = "recipeno", required = false) Integer recipeno, Model model) {
        // recipeno가 null이 아니라면 조회하고 결과를 모델에 담음
        sessionCheckService.sessionCheck(model, httpSession);
        if (recipeno != null) {
            RecipeEntity recipe = recipeService.findRecipeId(recipeno);
            if (recipe != null) {
                model.addAttribute("recipeDetailData", recipe);
            } else {
                // 해당 recipeno에 대한 레코드가 없을 경우에 대한 처리

            }
        } else {
            // recipeno가 제공되지 않은 경우에 대한 처리

        }

        return "recipeDetail";
    }

    // 레시피 작성(폼)
    @GetMapping("/recipeWrite")
    public String recipeWriteForm() {

        return "recipeWrite";
    }
}
