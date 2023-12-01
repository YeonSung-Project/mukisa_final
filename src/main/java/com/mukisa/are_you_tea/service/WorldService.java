package com.mukisa.are_you_tea.service;


import com.mukisa.are_you_tea.data.entity.WorldEntity;
import com.mukisa.are_you_tea.data.repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WorldService {

    @Autowired
    WorldRepository worldRepository;

    // 목록 조회
    public List<WorldEntity> getAllWorlds() {
        try {
            return worldRepository.findAll();
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // 세계 생성
    public WorldEntity createWorld(WorldEntity worldEntity) {
        try {
            return worldRepository.save(worldEntity);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    // 세계 수정
    public Optional<WorldEntity> updateWorld(int id, WorldEntity updatedWorld) {
        try {
            Optional<WorldEntity> existingWorld = worldRepository.findById(id);

            if (existingWorld.isPresent()) {
                WorldEntity worldToUpdate = existingWorld.get();
                // 여기에서 updatedWorld의 필드를 사용하여 worldToUpdate를 업데이트
                // 예: worldToUpdate.setName(updatedWorld.getName());

                return Optional.of(worldRepository.save(worldToUpdate));
            }

            return Optional.empty(); // 찾는 세계가 없을 경우
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // 세계 삭제
    public boolean deleteWorld(int id) {
        try {
            worldRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return false;
        }
    }
}