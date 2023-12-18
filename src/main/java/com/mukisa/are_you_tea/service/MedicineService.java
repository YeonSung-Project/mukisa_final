package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.MedicineEntity;
import com.mukisa.are_you_tea.data.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository medicineRepository;


    public List<MedicineEntity> dataLoad() {
        return medicineRepository.findAll();
    }

    // 레시피 타입으로 레시피 조회
    public List<MedicineEntity> getRecipesByType(String recipeType) {
        return medicineRepository.findByTeaCategory(recipeType);
    }

    // ID로 레시피 조회
    public MedicineEntity findChinaEntityById(int teano) {
        Optional<MedicineEntity> chinaOptional = medicineRepository.findById(teano);
        return chinaOptional.orElse(null);
    }

    // 레시피 타입 가져오기
    public List<String> getDistinctRecipeTypes() {
        return medicineRepository.findDistinctByTeaCategory();
    }

}
