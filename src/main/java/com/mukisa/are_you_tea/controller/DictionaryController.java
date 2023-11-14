package com.mukisa.are_you_tea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DictionaryController {

    @RequestMapping("/dictionary")
    public String godictionary() {
        return "dictionary";
    }


}
