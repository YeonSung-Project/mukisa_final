package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class noticeController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    SessionCheckService sessionCheckService;
    @GetMapping("/notice")
    public String noticeCon(Model model){
        /** �α��� üũ */
        sessionCheckService.sessionCheck(model, httpSession);


        return "notice";
    }

    @GetMapping("/noticeWrite")
    public String noticeWriteCon(){
        return "noticeWrite";
    }
}
