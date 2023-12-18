package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.service.MemDeleteService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MyPageController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    SessionCheckService sessionCheckService;

    @Autowired
    MemDeleteService memDelService;





    @GetMapping("/mypage")
    public String MyPageCon(Model model){
        sessionCheckService.sessionCheck(model, httpSession);
        return "mypage";
    }

    @GetMapping("/memDelete")
    public String MemDeleteCon(Model model){
        String mbId = (String) httpSession.getAttribute("userSession");

        memDelService.memDelService(mbId);

        model.addAttribute("message", "탈퇴되었습니다.");
        model.addAttribute("searchUrl","/");

        return "message";
    }
}
