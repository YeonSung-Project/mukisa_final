package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    //레시지 목록
    @GetMapping("/recipe")
    public String gorecipeList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

            // 키워드가 있는 경우 검색 수행
            if (keyword != null && !keyword.isEmpty()) {
                Page<RecipeEntity> recipePage = recipeService.dataLoad(keyword, page, size, sortBy);
                model.addAttribute("recipeData", recipePage.getContent());
                model.addAttribute("currentPage", recipePage.getNumber());
                model.addAttribute("totalPages", recipePage.getTotalPages());
            } else {
                // 키워드가 없는 경우 전체 목록을 페이징하여 가져오기
                Page<RecipeEntity> recipePage = recipeService.dataLoad(null, page, size, sortBy);
                model.addAttribute("recipeData", recipePage.getContent());
                model.addAttribute("currentPage", recipePage.getNumber());
                model.addAttribute("totalPages", recipePage.getTotalPages());
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
}
