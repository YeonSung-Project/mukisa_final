package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.service.RecipeService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String gorecipeList(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        try {
            int pageSize = 16;
            PageRequest pageRequest = PageRequest.of(page, pageSize);

            // 페이징 처리된 레시피 데이터 가져오기
            Page<RecipeEntity> recipePage = recipeService.getRecipeListWithPaging(pageRequest);

            // 페이징 정보 및 레시피 타입 추가
            model.addAttribute("paging", recipePage);
            model.addAttribute("RecipeTypes", recipeService.getDistinctRecipeTypes());

            // 레시피 목록 데이터가 비어있지 않은 경우에만 추가
            if (!recipePage.isEmpty()) {
                model.addAttribute("recipeData", recipePage.getContent());
            } else {
                System.out.println("데이터셋이 비어 있습니다.");
            }

            sessionCheckService.sessionCheck(model, httpSession);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "recipe";
    }

    //상세 레시피 보기
    @RequestMapping("/recipeDetail")
    public String gorecipeDetail(@RequestParam(name = "recipeno", required = false) Integer recipeno, Model model) {

        sessionCheckService.sessionCheck(model, httpSession);

        // recipeno가 null이 아니라면 조회하고 결과를 모델에 담음
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

    @GetMapping("/recipes")
    public String getRecipesByType(@RequestParam String recipeType, Model model) {
        List<RecipeEntity> recipes = recipeService.findByRecipeType(recipeType);
        model.addAttribute("recipes", recipes);
        return "recipe-list :: #recipesFragment"; // Thymeleaf fragment update
    }
}
