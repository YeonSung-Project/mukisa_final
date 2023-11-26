package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    // 커뮤니티 글 리스트
    @GetMapping("community")
    public String community(Model model) {

        model.addAttribute("list", communityService.communityList());

        return "community";
    }

    // 커뮤니티 특정 글 상세보기
    @GetMapping("communityview")    // localhost:8080/community?boNo=1
    public String communityView(Model model, Integer boNo) {

        model.addAttribute("communityview", communityService.communityView(boNo));
        return "communityview";
    }

    // 커뮤니티 글 작성(폼)
    @GetMapping("/communitywrite")
    public String communityWriteForm() {

        return "communitywrite";
    }

    // 커뮤니티 글 작성
    @PostMapping("/communitywritepro")
    public String communityWritePro (CommunityEntity communityEntity) {


        communityService.communityWrite(communityEntity);
        
        return "";
    }

    // 커뮤니티 특정 글 수정 (폼)
    @GetMapping("/communitymodify/{boNo}")
    public String communityModify(Model model,@PathVariable("boNo") Integer boNo){

        model.addAttribute("communityview", communityService.communityView(boNo));

        return "communitymodify";
    }

    // 커뮤니티 특정 글 수정
    @PostMapping("/communityupdate/{boNo}")
    public String communityUpdate (@PathVariable("boNo") Integer boNo, CommunityEntity communityEntity) {

        // 기존에 있던 글을 검색
        CommunityEntity communityTemp = communityService.communityView(boNo);
        System.out.printf(String.valueOf(communityTemp));

        // get: 새로은 내용 / set : 기존에 있던 내용에 덮어 씌우기
        communityTemp.setBoTitle(communityEntity.getBoTitle());         // 제목
        communityTemp.setBoContent(communityEntity.getBoContent());     // 내용

        communityService.communityWrite(communityTemp);

        return "redirect:/community";
    }


    // 커뮤니티 특정 글 삭제
    @GetMapping("/communitydelete")
    public String communityDelete(Integer boNo){

        communityService.communityDelete(boNo);
        return "redirect:/community";
    }
}

