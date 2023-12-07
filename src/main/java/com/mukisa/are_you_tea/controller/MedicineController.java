package com.mukisa.are_you_tea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// 약차 관련 페이지 컨트롤러 클래스입니다.
@Controller
public class MedicineController {
    @GetMapping("/medicine")
    public String medicineController(){
        return "medicine";
    }
}
