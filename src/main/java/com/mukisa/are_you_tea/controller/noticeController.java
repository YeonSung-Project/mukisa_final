package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.AdminEntity;
import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import com.mukisa.are_you_tea.service.AdminCheckService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class noticeController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    SessionCheckService sessionCheckService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    AdminCheckService adminCheckService;
    @GetMapping("/notice")
    public String noticeCon(Model model,
                            @PageableDefault(page = 0, size = 20, sort = "noNo", direction = Sort.Direction.DESC) Pageable pageable,  // ����¡ ó��:������� 20��, sort = ��ɷ� ���� ��Ƽ� ����? = boNo
                            String searchKeyword){

        Page<NoticeEntity> list = null;

        /** �α��� üũ */
        sessionCheckService.sessionCheck(model, httpSession);


        /** �˻��� if */
        if (searchKeyword == null) {
            /** �˻�� null ���� �� */
            list = noticeService.noticeList(pageable);
        } else {
            /** �˻�� ���� �� */
            list = noticeService.communitySearchList(searchKeyword, pageable);
        }


        /******************** ����¡ ó�� ***********************/
        int nowPage = list.getPageable().getPageNumber();       // 0���� �����ϱ� ������ + 1

        int startPage = Math.max(nowPage - 4, 0);
        int endPage =  Math.min(nowPage + 5, list.getTotalPages()-1);

        /** ���� �������� ���� �������� URL �߰� */
        String prevPageUrl = (nowPage == 1) ? "#" : "/notice?page=" + (nowPage - 1);
        String nextPageUrl = (nowPage == list.getTotalPages()) ? "#" : "/notice?page=" + (nowPage + 1);
        /*****************************************************/

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPageUrl", prevPageUrl);
        model.addAttribute("nextPageUrl", nextPageUrl);
        model.addAttribute("data_count", list.getTotalPages());

        System.out.println(nowPage);
        System.out.println(list.getTotalPages());
        System.out.println(endPage);
        return "notice";
    }

    @GetMapping("/noticeWrite")
    public String noticeWriteCon(Model model){
        /** �α��� üũ */
        boolean areYouAdmin = adminCheckService.adminCheckService(httpSession);
        if(areYouAdmin){
            return "noticeWrite";
        }
        else {
            model.addAttribute("message", "접근 권한이 없습니다.");    // 메세지
            model.addAttribute("searchUrl", "/notice");
            return "message";
        }
    }

    @PostMapping("/noticeWritePro")
    public String noticeWriteProCon(NoticeEntity noticeEntity, MultipartFile file, Model model)throws Exception{
        String mbId = (String) httpSession.getAttribute("userSession");

        noticeService.noticeWrite(noticeEntity, file, mbId);

        model.addAttribute("message", "글 작성이 완료되었습니다..");    // �޼���
        model.addAttribute("searchUrl", "/notice");             // �� �ۼ� �� community �̵�

        return "message";
    }
}
