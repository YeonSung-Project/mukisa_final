package com.mukisa.are_you_tea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class noticeController {

    @GetMapping("/notice")
    public String noticeCon(){
        return "notice";
    }
}
