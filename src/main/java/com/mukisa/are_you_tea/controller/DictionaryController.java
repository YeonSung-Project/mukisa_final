package com.mukisa.are_you_tea.controller;

import com.mukisa.are_you_tea.data.entity.DictionaryEntity;
import com.mukisa.are_you_tea.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;
    @RequestMapping("/dictionary")
    public String godictionary(Model model) {
        try {
            List<DictionaryEntity> dataset = dictionaryService.dataLoad();

            if (!dataset.isEmpty()) {
                // 모든 데이터를 모델에 추가
                for (int i = 0; i < dataset.size(); i++) {
                    model.addAttribute("dictionaryData", dataset);

                }

            } else {
                System.out.println("데이터셋이 비어 있습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "dictionary";
    }


}
