package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.AdminEntity;
import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.repository.AdminRepository;
import com.mukisa.are_you_tea.data.repository.UserRepository;
import com.mukisa.are_you_tea.service.CommunityService;
import com.mukisa.are_you_tea.service.SessionCheckService;
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
import org.apache.commons.lang3.StringUtils;



import javax.servlet.http.HttpSession;

/**
 * @packageName    : com.mukisa.are_you_tea.controller
 * @fileName        : CommunityController
 * @author        : Youil Park
 * @date            : 2023-11-26
 * @description            :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-26      Youil Park       최초 생성
 */

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionCheckService sessionCheckService;

    @Autowired
    private AdminRepository adminRepository;

    /**
     * @methodName : community
     * @description : 커뮤니티 글 리스트 조회
     * @author  : Youil Park
     * @param : model
     * @param : pageable        페이징 처리
     * @param : searchKeyword   검색어
     * @return : community
     */
    @GetMapping("community")
    public String community(Model model,
                            @PageableDefault(page = 0, size = 20, sort = "boNo", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword){ // 검색어

        Page<CommunityEntity> list = null;

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        /** 검색어 if */
        if (searchKeyword == null) {
            /** 검색어가 null 값일 때 */
            list = communityService.communityList(pageable);
        } else {
            /** 검색어가 있을 때 */
            list = communityService.communitySearchList(searchKeyword, pageable);
        }


        /******************** 페이징 처리 ***********************/
        int nowPage = list.getPageable().getPageNumber();


        int startPage = Math.max(nowPage - 4, 0);
        int endPage =  Math.min(nowPage + 5, list.getTotalPages()-1);

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
        model.addAttribute("data_count", list.getTotalPages());

        return "community";
    }

    /**
     * @methodName : communityView
     * @description : 커뮤니티 글 상세보기
     * @author  : Youil Park
     * @param : model
     * @param : boNo    글 번호
     * @return : communityview
     */
    @GetMapping("communityview")
    public String communityView(Model model, Integer boNo) {

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        // 특정 글 조회
        CommunityEntity communityEntity = communityService.communityView(boNo);

        // 파일이 없는 경우 메시지 설정
        if (communityEntity != null && StringUtils.isEmpty(communityEntity.getBoFilepath())) {
            communityEntity.setBoFilepath("파일이 없습니다.");
        }

        communityService.updateHits(boNo);  // 조회수
        
        model.addAttribute("communityview", communityService.communityView(boNo));
        return "communityview";
    }

    /**
     * @methodName : communityWriteForm
     * @description : 커뮤니티 글 작성 (폼)
     * @author  : Youil Park
     * @param : model
     * @return : communitywrite
     */
    @GetMapping("/communitywrite")
    public String communityWriteForm(Model model) {

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        return "communitywrite";
    }

    /**
     * @methodName : communityWriteForm
     * @description : 커뮤니티 글 작성
     * @author  : Youil Park
     * @param : communityEntity
     * @param : file        파일 처리
     * @param : model
     * @throws : Exception 예외 발생 시
     * @return : message
     */
    @PostMapping("/communitywritepro")
    public String communityWritePro (CommunityEntity communityEntity, MultipartFile file, Model model) throws Exception{

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        /** 세션에 있는 사용자 아이디 받기 */
        String mbId = (String) httpSession.getAttribute("userSession");

        /** 파일 */
        communityService.communityWrite(communityEntity, file, mbId);

        model.addAttribute("message", "글 작성이 완료되었습니다.");    // 메세지
        model.addAttribute("searchUrl", "/community");             // 글 작성 후 community 이동

        return "message";
    }

    /**
     * @methodName : communityModify
     * @description : 커뮤니티 특정 글 수정 (폼)
     * @author  : Youil Park
     * @param : model
     * @param : boNo    글 번호
     * @return : message
     */
    @GetMapping("/communitymodify/{boNo}")
    public String communityModify(Model model, @PathVariable("boNo") Integer boNo) {
        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        /** 세션에 저장된 사용자 아이디 가져오기 */
        String loggedInUserId = (String) httpSession.getAttribute("userSession");

        if (boNo == null) {
            /** boNo가 없는 경우 예외 처리 또는 메시지를 설정할 수 있습니다. */
            model.addAttribute("message", "글 번호가 없습니다.");
            model.addAttribute("searchUrl", "/community"); // 이동할 URL 설정
        } else {

            /** 글 정보 가져오기 */
            CommunityEntity communityEntity = communityService.communityView(boNo);

            if (communityEntity != null) {

                /** 어드민 */
                AdminEntity adminEntity = adminRepository.findByAdId(loggedInUserId);
                if (loggedInUserId != null && (loggedInUserId.equals(communityEntity.getMbId()) || adminEntity != null)) {
                    model.addAttribute("communityview", communityEntity);
                    return "communitymodify";
                } else {
                    /** 작성자나 mukisa가 아닌 경우에는 수정 권한이 없음을 메시지로 설정 */
                    model.addAttribute("message", "수정 권한이 없습니다.");
                }
            } else {

                /** 글이 존재하지 않는 경우 예외 처리 또는 메시지를 설정할 수 있습니다. */
                model.addAttribute("message", "수정할 글을 찾을 수 없습니다.");
            }

            model.addAttribute("searchUrl", "/community"); // 이동할 URL 설정
        }

        return "message";
    }


    /**
     * @methodName : communityUpdate
     * @description : 커뮤니티 특정 글 수정
     * @author  : Youil Park
     * @param : boNo    글 번호
     * @param : communityEntity
     * @param : file
     * @param : model
     * @throws : Exception 예외 발생 시
     * @return : message
     */
    @PostMapping("/communityupdate/{boNo}")
    public String communityUpdate (@PathVariable("boNo") Integer boNo, CommunityEntity communityEntity, MultipartFile file, Model model) throws Exception {

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        String mbId = (String) httpSession.getAttribute("userSession");

        /** 기존에 있던 글울 검색 */
        CommunityEntity communityTemp = communityService.communityView(boNo);
        System.out.printf(String.valueOf(communityTemp));

        /**
         *  get : 새로운 내용 (새로운 글 제목, 새로운 글 내용)
         *  set : 기존에 있던 내용에 덮에 씌우기
         */
        communityTemp.setBoTitle(communityEntity.getBoTitle());         // 제목
        communityTemp.setBoContent(communityEntity.getBoContent());     // 내용

        communityService.communityWrite(communityTemp, file, mbId);

        model.addAttribute("message", "글 수정이 완료되었습니다.");    // 메세지
        model.addAttribute("searchUrl", "/community");             // 글 작성 후 community 이동

        return "message";
    }


    /**
     * @methodName : communityDelete
     * @description : 커뮤니티 특정 글 삭제
     * @author  : Youil Park
     * @param : model
     * @param : boNo
     * @return : message
     */
    @GetMapping("/communitydelete")
    public String communityDelete(Model model, Integer boNo) {

        /** 로그인 체크 */
        sessionCheckService.sessionCheck(model, httpSession);

        /** 세션에 저장된 사용자 아이디 가져오기 */
        String loggedInUserId = (String) httpSession.getAttribute("userSession");

        if (boNo == null) {
            // boNo가 없는 경우 예외 처리 또는 메시지를 설정할 수 있습니다.
            model.addAttribute("message", "글 번호가 없습니다.");
            model.addAttribute("searchUrl", "/community"); // 글 삭제 후 community 이동
        } else {

            /** 글 정보 가져오기 */
            CommunityEntity communityEntity = communityService.communityView(boNo);

            /** 어드민 */
            AdminEntity adminEntity = adminRepository.findByAdId(loggedInUserId);
            if (communityEntity != null && loggedInUserId != null && loggedInUserId.equals(communityEntity.getMbId()) || adminEntity != null) {
                // 현재 로그인한 사용자와 글의 작성자가 일치하는 경우에만 삭제 수행
                communityService.communityDelete(boNo);
                model.addAttribute("message", "삭제 되었습니다.");
            } else {
                // 작성자가 아닌 경우에는 삭제 권한이 없음을 메시지로 설정
                model.addAttribute("message", "삭제 권한이 없습니다.");
            }

            model.addAttribute("searchUrl", "/community"); // 글 삭제 후 community 이동
        }

        return "message";
    }


}

