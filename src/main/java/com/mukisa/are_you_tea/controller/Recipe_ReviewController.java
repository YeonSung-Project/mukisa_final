package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.service.CommunityService;
import com.mukisa.are_you_tea.service.Recipe_ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


@Controller
public class Recipe_ReviewController {

    @Autowired
    private Recipe_ReviewService recipeReviewService;

    @Autowired
    private HttpSession httpSession;




}

