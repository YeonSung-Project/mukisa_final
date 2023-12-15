package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import com.mukisa.are_you_tea.service.NoticeService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class noticeController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    SessionCheckService sessionCheckService;
    @Autowired
    NoticeService noticeService;
    @GetMapping("/notice")
    public String noticeCon(Model model,
                            @PageableDefault(page = 0, size = 20, sort = "noNo", direction = Sort.Direction.DESC) Pageable pageable,  // 페이징 처리:사이즈는 20개, sort = 어떤걸로 기준 삼아서 정렬? = boNo
                            String searchKeyword){

        Page<NoticeEntity> list = null;

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);


        /** 검색어 if */
        if (searchKeyword == null) {
            /** 검색어가 null 값일 때 */
            list = noticeService.noticeList(pageable);
        } else {
            /** 검색어가 있을 때 */
            list = noticeService.communitySearchList(searchKeyword, pageable);
        }


        /******************** 페이징 처리 ***********************/
        int nowPage = list.getPageable().getPageNumber()+1;       // 0에서 시작하기 때문에 + 1
        int startPage;
        int endPage;
        if(nowPage == 1){
            startPage = 1;
            endPage = 1;
        }
        else{
            startPage = Math.max(nowPage - 4, 1);
            endPage =  Math.min(nowPage + 5, list.getTotalPages());
        }

        /** 이전 페이지와 다음 페이지의 URL 추가 */
        String prevPageUrl = (nowPage == 1) ? "#" : "/community?page=" + (nowPage - 1);
        String nextPageUrl = (nowPage == list.getTotalPages()) ? "#" : "/community?page=" + (nowPage + 1);
        /*****************************************************/

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPageUrl", prevPageUrl);
        model.addAttribute("nextPageUrl", nextPageUrl);


        return "notice";
    }

    @GetMapping("/noticeWrite")
    public String noticeWriteCon(){
        return "noticeWrite";
    }
}
