package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.ChinaEntity;
import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.data.entity.Recipe_ReviewEntity;
import com.mukisa.are_you_tea.data.repository.ChinaRepository;
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
    ChinaRepository chinaRepository;
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



    //상세 레시피 보기
    @RequestMapping("/chinaDetail")
    public String gorecipeDetail(@RequestParam(name = "cteano", required = false) Integer cteano, Model model) {
        try {
            sessionCheckService.sessionCheck(model, httpSession);

            // recipeno가 null이 아니라면 조회하고 결과를 모델에 담음
            if (cteano != null) {
                ChinaEntity recipe = chinaService.findChinaEntityById(cteano);
                if (recipe != null) {
                    model.addAttribute("recipeDetailData", recipe);
                    int count_all = chinaRepository.findAll().size();
                    model.addAttribute("count_recipe_item", count_all);

                    System.out.println("dddddddddddddddddd"+count_all);


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

        return "chinaDetail";
    }
}
