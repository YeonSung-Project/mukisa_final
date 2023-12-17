package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.ChinaEntity;
import com.mukisa.are_you_tea.data.repository.ChinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChinaService {

    @Autowired
    private ChinaRepository chinaRepository;

    public List<ChinaEntity> dataLoad() {
        return chinaRepository.findAll();
    }





}
