package com.mukisa.are_you_tea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// ���� ���� ������ ��Ʈ�ѷ� Ŭ�����Դϴ�.
@Controller
public class MedicineController {
    @GetMapping("/medicine")
    public String medicineController(){
        return "medicine";
    }
}
