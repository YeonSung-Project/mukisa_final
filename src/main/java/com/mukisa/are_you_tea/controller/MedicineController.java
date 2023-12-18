package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.MedicineEntity;
import com.mukisa.are_you_tea.service.AdminCheckService;
import com.mukisa.are_you_tea.service.MedicineService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;



@Controller
public class MedicineController {
    @Autowired
    MedicineService medicineService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionCheckService sessionCheckService;
    @Autowired
    AdminCheckService adminCheckService;

    @RequestMapping("/medicine")
    public String gochinaList(@RequestParam(name = "recipeType", required = false) String recipeType,
                              Model model, HttpServletRequest request
    ) {
        try {

            List<MedicineEntity> recipeData;

            sessionCheckService.sessionCheck(model, httpSession);

            if (recipeType != null && !recipeType.isEmpty()) {

                recipeData = medicineService.getRecipesByType(recipeType);
            } else {

                recipeData = medicineService.dataLoad();
            }
            model.addAttribute("recipeData", recipeData);

            model.addAttribute("RecipeTypes", medicineService.getDistinctRecipeTypes());


            model.addAttribute("selectedRecipeType", recipeType);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "medicine";
    }



    @RequestMapping("/medicineDetail")
    public String gorecipeDetail(@RequestParam(name = "teano", required = false) Integer teano, Model model) {
        try {
            sessionCheckService.sessionCheck(model, httpSession);


            if (teano != null) {
                MedicineEntity recipe = medicineService.findChinaEntityById(teano);
                if (recipe != null) {
                    model.addAttribute("recipeDetailData", recipe);


                } else {

                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return "medicineDetail";
    }


}
