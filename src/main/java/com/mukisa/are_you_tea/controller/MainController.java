package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.MemberEntity;
import com.mukisa.are_you_tea.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String mainPageController(Model model) {
        String mbId = (String) httpSession.getAttribute("userSession");
        if (mbId != null) {
            MemberEntity member = memberRepository.findByMbId(mbId);
            model.addAttribute("member", member.getMbId());
        }
        return "main";
    }
}
