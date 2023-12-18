package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.AdminEntity;
import com.mukisa.are_you_tea.data.entity.MemberEntity;
import com.mukisa.are_you_tea.data.entity.UserEntity;
import com.mukisa.are_you_tea.data.repository.AdminRepository;
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

    @Autowired
    AdminRepository adminRepository;

    public void sessionCheck(Model model, HttpSession httpSession){
        String mbId = (String) httpSession.getAttribute("userSession");
        if (mbId != null) {

            AdminEntity admin = adminRepository.findByAdId(mbId);
            if(admin != null){
                model.addAttribute("member", admin.getAdId());
                model.addAttribute("member_role", admin.getRole());
                model.addAttribute("memberYn", null);
                System.out.println("어드민 접속");
            }
            MemberEntity member = memberRepository.findByMbId(mbId);
            if(member != null){
                model.addAttribute("member", member.getMbId());
                model.addAttribute("memberYn", member.getMbYn());
                System.out.println("로컬 유저 접속");
            }
            UserEntity user = userRepository.findByUsername(mbId);
            if(user != null){
                model.addAttribute("member", user.getUsername());
                model.addAttribute("memberYn", null);
                System.out.println("플랫폼 유저 접속");
            }
        }
    }
}
