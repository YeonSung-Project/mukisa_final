package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository MedicineRepository;


}
