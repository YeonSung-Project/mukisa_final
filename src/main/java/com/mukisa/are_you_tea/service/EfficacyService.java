package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.EfficacyEntity;
import com.mukisa.are_you_tea.data.repository.EfficacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EfficacyService {

    @Autowired
    EfficacyRepository efficacyRepository;

    public List<EfficacyEntity> dataLoad(){
        try {
            return efficacyRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
