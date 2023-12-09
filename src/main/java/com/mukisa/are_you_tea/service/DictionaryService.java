package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.DictionaryEntity;
import com.mukisa.are_you_tea.data.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DictionaryService {

    @Autowired
    DictionaryRepository dictionaryRepository;

    //목록 조회에 사용
    public List<DictionaryEntity> getAllDictionaries(){
        try {
            return dictionaryRepository.findAll();
        } catch (Exception e){
            // 예외 처리
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // 용어 생성
    public DictionaryEntity createDictionary(DictionaryEntity dictionaryEntity) {
        try {
            return dictionaryRepository.save(dictionaryEntity);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    // 용어 수정
    public Optional<DictionaryEntity> updateDictionary(int id, DictionaryEntity updatedDictionary) {
        try {
            Optional<DictionaryEntity> existingDictionary = dictionaryRepository.findById(id);

            if (existingDictionary.isPresent()) {
                DictionaryEntity dictionaryToUpdate = existingDictionary.get();
                // 여기에서 updatedDictionary의 필드를 사용하여 dictionaryToUpdate를 업데이트
                // 예: dictionaryToUpdate.setName(updatedDictionary.getName());

                return Optional.of(dictionaryRepository.save(dictionaryToUpdate));
            }

            return Optional.empty(); // 찾는 용어가 없을 경우
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // 용어 삭제
    public boolean deleteDictionary(int id) {
        try {
            dictionaryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return false;
        }
    }
}
