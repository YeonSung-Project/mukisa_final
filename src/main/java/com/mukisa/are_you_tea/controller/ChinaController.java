package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.ChinaEntity;
import com.mukisa.are_you_tea.service.AdminCheckService;
import com.mukisa.are_you_tea.service.ChinaService;
import com.mukisa.are_you_tea.service.SessionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// 중국차 관련 페이지 컨트롤러 클래스입니다.
@Controller
public class ChinaController {

    @Autowired
    ChinaService chinaService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionCheckService sessionCheckService;
    @Autowired
    AdminCheckService adminCheckService;

    @RequestMapping("/china")
    public String goChinaList(@RequestParam(name = "teaType", required = false) String teaType,
                              Model model, HttpServletRequest request,
                              @PageableDefault(page = 0, size = 20, sort = "CTEA_NO", direction = Sort.Direction.DESC) Pageable pageable,
                              String searchKeyword
    ) {
        try {
            Page<ChinaEntity> list = null;
            List<ChinaEntity> chinaData;


            sessionCheckService.sessionCheck(model, httpSession);

                chinaData = chinaService.dataLoad();



            // 추출한 teaType을 모델에 추가
            model.addAttribute("chinaTea",chinaData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "china";
    }
}
