package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.DictionaryEntity;
import com.mukisa.are_you_tea.data.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    @Autowired
    DictionaryRepository dictionaryRepository;

    public List<DictionaryEntity> dataLoad(){
        try {
            return dictionaryRepository.findAll();
        }catch (Exception e){
            return null;
        }

    }
}
