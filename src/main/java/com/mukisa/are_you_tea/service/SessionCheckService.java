package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.MemberEntity;
import com.mukisa.are_you_tea.data.entity.UserEntity;
import com.mukisa.are_you_tea.data.repository.MemberRepository;
import com.mukisa.are_you_tea.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class SessionCheckService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    UserRepository userRepository;

    public void sessionCheck(Model model, HttpSession httpSession){
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
    }
}
