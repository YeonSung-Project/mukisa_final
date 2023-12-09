package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.EfficacyEntity;
import com.mukisa.are_you_tea.service.EfficacyService;
import com.mukisa.are_you_tea.service.MemberService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class EfficacyController {

    @Autowired
    EfficacyService efficacyService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionCheckService sessionCheckService;
    @RequestMapping("/efficacy")
    public String goefficacy(Model model) {
        sessionCheckService.sessionCheck(model, httpSession);

        try {
            List<EfficacyEntity> dataset = efficacyService.dataLoad();

            if (!dataset.isEmpty()) {
                // 모든 데이터를 모델에 추가
                for (int i = 0; i < dataset.size(); i++) {
                    model.addAttribute("efficacyData" , dataset);

                }

            } else {
                System.out.println("데이터셋이 비어 있습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "efficacy";
    }





}
