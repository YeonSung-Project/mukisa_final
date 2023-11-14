package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class EfficacyController {

    @RequestMapping("/efficacy")
    public String goefficacy() {
        return "efficacy";
    }


}
