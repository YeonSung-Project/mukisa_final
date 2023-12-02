package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.service.CommunityService;
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

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    // 커뮤니티 글 리스트
    @GetMapping("community")                           // 사이즈는 20개, sort = 어떤걸로 기준 삼아서 정렬? = boNo
    public String community(Model model,
                            @PageableDefault(page = 0, size = 20, sort = "boNo", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<CommunityEntity> list = null;

        // 검색어 if
        if (searchKeyword == null) {
            // 검색어가 안 들어왔을 때
            list = communityService.communityList(pageable);
        } else {
            // 검색어가 들어왔을 때
            list = communityService.communitySearchList(searchKeyword, pageable);
        }


        // ************ 페이징 처리 *******************
        int nowPage = list.getPageable().getPageNumber() + 1;       // 0에서 시작하기 때문에 + 1
        int startPage = Math.max(nowPage - 4, 1);
        int endPage =  Math.min(nowPage + 5, list.getTotalPages());
        // ******************************************

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
    public String communityWritePro (CommunityEntity communityEntity, MultipartFile file, Model model) throws Exception{

        communityService.communityWrite(communityEntity, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");    // 메세지
        model.addAttribute("searchUrl", "/community");             // 글 작성 후 community 이동

        return "message";
    }

    // 커뮤니티 특정 글 수정 (폼)
    @GetMapping("/communitymodify/{boNo}")
    public String communityModify(Model model,@PathVariable("boNo") Integer boNo){

        model.addAttribute("communityview", communityService.communityView(boNo));

        return "communitymodify";
    }

    // 커뮤니티 특정 글 수정
    @PostMapping("/communityupdate/{boNo}")
    public String communityUpdate (@PathVariable("boNo") Integer boNo, CommunityEntity communityEntity, MultipartFile file, Model model) throws Exception {

        // 기존에 있던 글을 검색
        CommunityEntity communityTemp = communityService.communityView(boNo);
        System.out.printf(String.valueOf(communityTemp));

        // get: 새로은 내용 / set : 기존에 있던 내용에 덮어 씌우기
        communityTemp.setBoTitle(communityEntity.getBoTitle());         // 제목
        communityTemp.setBoContent(communityEntity.getBoContent());     // 내용

        communityService.communityWrite(communityTemp, file);

        model.addAttribute("message", "글 수정이 완료되었습니다.");    // 메세지
        model.addAttribute("searchUrl", "/community");             // 글 작성 후 community 이동

        return "message";
    }


    // 커뮤니티 특정 글 삭제
    @GetMapping("/communitydelete")
    public String communityDelete(Integer boNo){

        communityService.communityDelete(boNo);
        return "redirect:/community";
    }
}

