package com.mukisa.are_you_tea.service;


import com.mukisa.are_you_tea.data.entity.WorldEntity;
import com.mukisa.are_you_tea.data.repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldService {

    @Autowired
    WorldRepository worldRepository;

    public List<WorldEntity> dataLoad(){
        try {
            return worldRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
