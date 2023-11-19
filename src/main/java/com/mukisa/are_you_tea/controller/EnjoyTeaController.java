package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.EnjoyEntity;
import com.mukisa.are_you_tea.service.EnjoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EnjoyTeaController {
    @Autowired
    EnjoyService enjoyService;

    @RequestMapping("/enjoy")
    public String goenjoy(Model model) {
        try {
            List<EnjoyEntity> dataset = enjoyService.dataLoad();

            if (!dataset.isEmpty()) {
                // 모든 데이터를 모델에 추가
                for (int i = 0; i < dataset.size(); i++) {
                    model.addAttribute("enjoyData" , dataset);

                }

            } else {
                System.out.println("데이터셋이 비어 있습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "enjoy";
    }


}
