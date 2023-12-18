package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.AdminEntity;
import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import com.mukisa.are_you_tea.data.repository.AdminRepository;
import com.mukisa.are_you_tea.service.AdminCheckService;
import com.mukisa.are_you_tea.service.NoticeService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.apache.commons.lang3.StringUtils;
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
public class noticeController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    SessionCheckService sessionCheckService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    AdminCheckService adminCheckService;

    @Autowired
    AdminRepository adminRepository;

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
            searchKeyword.replaceAll("\\s","");
            list = noticeService.noticeSearchList(searchKeyword, pageable);
        }


        /******************** ����¡ ó�� ***********************/
        int nowPage = list.getPageable().getPageNumber();       // 0���� �����ϱ� ������ + 1

        int startPage = Math.max(nowPage - 4, 0);
        int endPage =  Math.min(nowPage + 5, list.getTotalPages()-1);

        /** ���� �������� ���� �������� URL �߰� */
        String prevPageUrl = (nowPage == 1) ? "#" : "/notice?page=" + (nowPage - 1);
        String nextPageUrl = (nowPage == list.getTotalPages()) ? "#" : "/notice?page=" + (nowPage + 1);
        /*****************************************************/

        if(list.isEmpty()){
            list =null;
        }
        if(list != null){

            model.addAttribute("data_count", list.getTotalPages());
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("prevPageUrl", prevPageUrl);
            model.addAttribute("nextPageUrl", nextPageUrl);
            System.out.println(list.getTotalPages());
            System.out.println(nowPage);
            System.out.println(endPage);
        }

        model.addAttribute("list", list);
        System.out.println(list);
        return "notice";
    }

    @GetMapping("/noticeWrite")
    public String noticeWriteCon(Model model){
        /** �α��� üũ */
        sessionCheckService.sessionCheck(model, httpSession);
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

    @GetMapping("noticeView")
    public String communityView(Model model, Integer noNo) {

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        // 특정 글 조회
        NoticeEntity noticeEntity = noticeService.noticeView(noNo);


        // 파일이 없는 경우 메시지 설정
        if (noticeEntity != null && StringUtils.isEmpty(noticeEntity.getNoFilepath())) {
            noticeEntity.setNoFilepath("파일이 없습니다.");
        }

        noticeService.updateHits(noNo);  // 조회수 증가

        model.addAttribute("noticeData", noticeService.noticeView(noNo));
        return "noticeView";
    }


    @GetMapping("/noticeModify/{noNo}")
    public String noticeModifyCon(Model model, @PathVariable("noNo") Integer noNo){

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);
        /** 세션에 저장된 사용자 아이디 가져오기 */
        String loggedInUserId = (String) httpSession.getAttribute("userSession");

        if (noNo == null) {
            /** noNo가 없는 경우 예외 처리 또는 메시지를 설정할 수 있습니다. */
            model.addAttribute("message", "글 번호가 없습니다.");
            model.addAttribute("searchUrl", "/notice"); // 이동할 URL 설정
        } else {

            /** 글 정보 가져오기 */
            NoticeEntity noticeEntity = noticeService.noticeView(noNo);

            if (noticeEntity != null) {
                /** 어드민 */
                AdminEntity adminEntity = adminRepository.findByAdId(loggedInUserId);
                if (loggedInUserId != null && (loggedInUserId.equals(noticeEntity.getNoId()) || adminEntity != null)) {
                    model.addAttribute("noticeData", noticeEntity);
                    return "noticeModify";
                } else {
                    /** 작성자나 관리자가 아닌 경우에는 수정 권한이 없음을 메시지로 설정 */
                    model.addAttribute("message", "수정 권한이 없습니다.");
                }
            } else {
                /** 글이 존재하지 않는 경우 예외 처리 또는 메시지를 설정할 수 있습니다. */
                model.addAttribute("message", "수정할 글을 찾을 수 없습니다.");
            }
            model.addAttribute("searchUrl", "/notice"); // 이동할 URL 설정
        }
        return "message";
    }

    @PostMapping("/noticeModifyPro/{noNo}")
    public String noticeModifyPro(@PathVariable("noNo") Integer noNo, NoticeEntity nowNoticeEntity, MultipartFile file, Model model) throws Exception {

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        String mbId = (String) httpSession.getAttribute("userSession");

        /** 기존에 있던 글울 검색 */
        NoticeEntity noticeEntityHistory = noticeService.noticeView(noNo);
        System.out.printf(String.valueOf(noticeEntityHistory));

        /**
         *  get : 새로운 내용 (새로운 글 제목, 새로운 글 내용)
         *  set : 기존에 있던 내용에 덮에 씌우기
         */
        noticeEntityHistory.setNoTitle(nowNoticeEntity.getNoTitle());         // 제목
        noticeEntityHistory.setNoContent(nowNoticeEntity.getNoContent());     // 내용

        noticeService.noticeWrite(noticeEntityHistory, file, mbId);

        model.addAttribute("message", "글 수정이 완료되었습니다.");    // 메세지
        model.addAttribute("searchUrl", "/notice");             // 글 작성 후 community 이동

        return "message";
    }

    @GetMapping("/noticeDeletePro")
    public String noticeDeleteCon(Model model, Integer noNo) {

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        /** 세션에 저장된 사용자 아이디 가져오기 */
        String loggedInUserId = (String) httpSession.getAttribute("userSession");

        if (noNo == null) {
            // boNo가 없는 경우 예외 처리 또는 메시지를 설정할 수 있습니다.
            model.addAttribute("message", "글 번호가 없습니다.");
            model.addAttribute("searchUrl", "/notice");
        } else {

            /** 글 정보 가져오기 */
            NoticeEntity noticeEntity = noticeService.noticeView(noNo);

            /** 어드민 */
            AdminEntity adminEntity = adminRepository.findByAdId(loggedInUserId);
            if (noticeEntity != null && loggedInUserId != null && loggedInUserId.equals(noticeEntity.getNoId()) || adminEntity != null) {
                // 현재 로그인한 사용자와 글의 작성자가 일치하는 경우에만 삭제 수행
                noticeService.noticeDelete(noNo);
                model.addAttribute("message", "삭제 되었습니다.");
            } else {
                // 작성자가 아닌 경우에는 삭제 권한이 없음을 메시지로 설정
                model.addAttribute("message", "삭제 권한이 없습니다.");
            }

            model.addAttribute("searchUrl", "/notice"); // 글 삭제 후 community 이동
        }

        return "message";
    }
}
