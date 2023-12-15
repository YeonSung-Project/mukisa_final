package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.repository.ChinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChinaService {

    @Autowired
    ChinaRepository chinaRepository;


}
