package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.config.auth.PrincipalDetailsService;
import com.mukisa.are_you_tea.data.entity.MemberEntity;
import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import com.mukisa.are_you_tea.data.entity.UserEntity;
import com.mukisa.are_you_tea.data.repository.MemberRepository;
import com.mukisa.are_you_tea.data.repository.UserRepository;
import com.mukisa.are_you_tea.service.RecipeService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Autowired
    private RecipeService recipeService;

    @Autowired
    SessionCheckService sessionCheckService;

    @GetMapping("/")
    public String mainPageController(Model model) {

        /** ?Ґб??? ?? */
        sessionCheckService.sessionCheck(model, httpSession);

        //        ???? ?????? ?????? ???????
        try {
            List<RecipeEntity> dataset = recipeService.dataLoad();

            if (!dataset.isEmpty()) {
                // ????????? 5?? ????? ??До?? ???? 5???? ??????? ??? ???
                if (dataset.size() >= 5) {
                    List<RecipeEntity> top5Recipes = dataset.subList(0, 4);
                    model.addAttribute("recipeData", top5Recipes);
                } else {
                    // ????????? 5?? ????? ??? ??? ??????? ??? ???
                    model.addAttribute("recipeData", dataset);
                }
            } else {
                System.out.println("????????? ??? ??????.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }
}