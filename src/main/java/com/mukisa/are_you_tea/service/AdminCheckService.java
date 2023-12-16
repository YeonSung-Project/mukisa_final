package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.AdminEntity;
import com.mukisa.are_you_tea.data.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminCheckService {

    @Autowired
    AdminRepository adminRepository;

    public boolean adminCheckService(HttpSession httpSession){
        String mbId = (String) httpSession.getAttribute("userSession");
        AdminEntity admin = adminRepository.findByAdId(mbId);
        if(admin != null){
            return true;
        }
        return false;
    }
}
