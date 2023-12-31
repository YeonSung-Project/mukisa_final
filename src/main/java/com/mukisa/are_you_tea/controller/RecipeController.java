package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.data.entity.Recipe_ReviewEntity;
import com.mukisa.are_you_tea.data.repository.RecipeRepository;
import com.mukisa.are_you_tea.service.AdminCheckService;
import com.mukisa.are_you_tea.service.RecipeService;
import com.mukisa.are_you_tea.service.Recipe_ReviewService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    Recipe_ReviewService recipe_reviewService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionCheckService sessionCheckService;
    @Autowired
    AdminCheckService adminCheckService;
    @Autowired
    private RecipeRepository recipeRepository;

    @RequestMapping("/recipe")
    public String gorecipeList(@RequestParam(name = "recipeType", required = false) String recipeType,
                               Model model, HttpServletRequest request,
    @PageableDefault(page = 0, size = 20, sort = "recipeNo", direction = Sort.Direction.DESC) Pageable pageable,
                               String searchKeyword
    ) {
        try {
            Page<RecipeEntity> list = null;
            List<RecipeEntity> recipeData;

            sessionCheckService.sessionCheck(model, httpSession);



            if (recipeType != null && !recipeType.isEmpty()) {
                // recipeType이 주어진 경우 해당 타입의 레시피만 조회
                recipeData = recipeService.getRecipesByType(recipeType);
            } else {
                // recipeType이 주어지지 않은 경우 전체 레시피 조회
                recipeData = recipeService.dataLoad();
            }

            // 레시피 타입 추가
            model.addAttribute("RecipeTypes", recipeService.getDistinctRecipeTypes());

            // 레시피 목록 데이터가 비어있지 않은 경우에만 추가
            if (!recipeData.isEmpty()) {

                model.addAttribute("recipeData", recipeData);

            } else {
                System.out.println("데이터셋이 비어 있습니다.");
            }

            // 추출한 recipeType을 모델에 추가
            model.addAttribute("selectedRecipeType", recipeType);






        } catch (Exception e) {
            e.printStackTrace();
        }

        return "recipe";
    }


    //상세 레시피 보기
    @RequestMapping("/recipeDetail")
    public String gorecipeDetail(@RequestParam(name = "recipeno", required = false) Integer recipeno, Model model) {
        try {
            sessionCheckService.sessionCheck(model, httpSession);

            // recipeno가 null이 아니라면 조회하고 결과를 모델에 담음
            if (recipeno != null) {
                RecipeEntity recipe = recipeService.findRecipeId(recipeno);
                if (recipe != null) {

                    model.addAttribute("recipeDetailData", recipe);



                    // 해당 레시피에 대한 댓글 목록을 가져옵니다.
                    List<Recipe_ReviewEntity> recipeReviews = recipe_reviewService.getRecipeReviewByRecipeId(recipeno);

                    // 댓글 목록을 모델에 추가합니다.
                    model.addAttribute("recipeReviews", recipeReviews);
                } else {
                    // 해당 recipeno에 대한 레코드가 없을 경우에 대한 처리
                    // 메시지를 추가하거나 다른 작업 수행
                }
            } else {
                // recipeno가 제공되지 않은 경우에 대한 처리
                // 메시지를 추가하거나 다른 작업 수행
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 - 로깅이나 사용자에게 오류 메시지 보여주기 등
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
        try {
            List<RecipeEntity> recipes;



            // 선택한 유형에 따라 레시피 로드
            if ("all".equals(recipeType)) {
                recipes = recipeService.dataLoad();
            } else {
                recipes = recipeService.findByRecipeType(recipeType);
            }

            // 모델에 레시피 추가
            model.addAttribute("recipeData", recipes);

            // 프래그먼트 업데이트 반환
            return "recipe-list :: #recipesFragment";
        } catch (Exception e) {
            // 예외 로그 남기기


            // 에러 페이지로 리다이렉트하거나 다른 방식으로 처리할 수 있습니다.
            return "error";
        }
    }



}
