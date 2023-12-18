package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.ChinaEntity;
import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.service.AdminCheckService;
import com.mukisa.are_you_tea.service.ChinaService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// 중국차 관련 페이지 컨트롤러 클래스입니다.
@Controller
public class ChinaController {

    @Autowired
    ChinaService chinaService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionCheckService sessionCheckService;
    @Autowired
    AdminCheckService adminCheckService;

    @RequestMapping("/china")
    public String gochinaList(@RequestParam(name = "recipeType", required = false) String recipeType,
                               Model model, HttpServletRequest request
    ) {
        try {

            List<ChinaEntity> recipeData;

            sessionCheckService.sessionCheck(model, httpSession);

            if (recipeType != null && !recipeType.isEmpty()) {
                // recipeType이 주어진 경우 해당 타입의 레시피만 조회
                recipeData = chinaService.getRecipesByType(recipeType);
            } else {
                // recipeType이 주어지지 않은 경우 전체 레시피 조회
                recipeData = chinaService.dataLoad();
            }
            model.addAttribute("recipeData", recipeData);
            // 레시피 타입 추가
            model.addAttribute("RecipeTypes", chinaService.getDistinctRecipeTypes());

            // 추출한 recipeType을 모델에 추가
            model.addAttribute("selectedRecipeType", recipeType);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "china";
    }
}
