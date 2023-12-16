package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String loginController() {
        return "login";
    }

    @PostMapping("/loginPro")
    public @ResponseBody String login(@RequestParam("mbId") String mbId, @RequestParam("mbPw") String mbPw, Model model) {
        boolean loginSuccess = memberService.login(mbId, mbPw);
        if (loginSuccess) {
            httpSession.setAttribute("userSession", mbId);
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    @GetMapping("/step01")
    public String step01Controller(){
        return "step01";
    }
    @GetMapping("/signup")
    public String signupController() {
        return "signup";
    }

    @PostMapping("/signupPro")
    public @ResponseBody String signup(@RequestParam("mbId") String mbId, @RequestParam("mbPw") String mbPw, @RequestParam("mbNm") String mbNm) {
        int signupSuccess = memberService.signup(mbId, mbPw, mbNm);
        switch (signupSuccess) {
            case 0:
                return "FAIL_ID";
            case 1:
                return "SUCCESS";
            case 3:
                return "FAIL_PW_MATCHER";
            case 4:
                return "FAIL_ADID_MATCHER";
            case 5:
                return "FAIL_ADID_to_MBNM_MATCHER";
            default:
                return "FAIL";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        memberService.logout(httpSession);
        return "redirect:/";
    }


}
