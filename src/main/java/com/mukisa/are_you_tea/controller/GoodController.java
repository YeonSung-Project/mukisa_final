package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.GoodEntity;
import com.mukisa.are_you_tea.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GoodController {

    private final GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }



}
