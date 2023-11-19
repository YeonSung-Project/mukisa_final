package com.mukisa.are_you_tea.service;


import com.mukisa.are_you_tea.data.entity.EnjoyEntity;
import com.mukisa.are_you_tea.data.repository.EnjoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnjoyService {

    @Autowired
    EnjoyRepository enjoyRepository;

    public List<EnjoyEntity> dataLoad(){
        try {
            return enjoyRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
