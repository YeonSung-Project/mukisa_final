package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.ChinaEntity;
import com.mukisa.are_you_tea.data.repository.ChinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChinaService {

    @Autowired
    private ChinaRepository chinaRepository;

    public List<ChinaEntity> dataLoad() {
        return chinaRepository.findAll();
    }

    // 레시피 타입으로 레시피 조회
    public List<ChinaEntity> getRecipesByType(String recipeType) {
        return chinaRepository.findByCteaCategory(recipeType);
    }

    // ID로 레시피 조회
    public ChinaEntity findChinaEntityById(int cteano) {
        Optional<ChinaEntity> chinaOptional = chinaRepository.findById(cteano);
        return chinaOptional.orElse(null);
    }

    // 레시피 타입 가져오기
    public List<String> getDistinctRecipeTypes() {
        return chinaRepository.findDistinctByCteaCategory();
    }

}
