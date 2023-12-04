package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.config.auth.PrincipalDetailsService;
import com.mukisa.are_you_tea.data.entity.MemberEntity;
import com.mukisa.are_you_tea.data.entity.UserEntity;
import com.mukisa.are_you_tea.data.repository.MemberRepository;
import com.mukisa.are_you_tea.data.repository.UserRepository;
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

    @Autowired
    private PrincipalDetailsService principalDetailsService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String mainPageController(Model model) {
        String mbId = (String) httpSession.getAttribute("userSession");
        if (mbId != null) {
            MemberEntity member = memberRepository.findByMbId(mbId);
            if(member != null){
                model.addAttribute("member", member.getMbId());
            }
            UserEntity user = userRepository.findByUsername(mbId);
            if(user != null){
                model.addAttribute("member", user.getUsername());
            }
        }
        return "main";
    }
}