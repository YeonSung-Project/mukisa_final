package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.repository.CommunityRepository;
import com.mukisa.are_you_tea.data.repository.EnjoyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    // 커뮤니티 글 리스트
    public List<CommunityEntity> communityList() {

        return communityRepository.findAll();
    }

    // 커뮤니티 특정 글 내용 보기
    public CommunityEntity communityView(Integer boNo) {

        // 글 번호 받아오기
        return communityRepository.findById(boNo).get();
    }

    // 커뮤니티 글 작성
    public void communityWrite(CommunityEntity communityEntity){
        communityRepository.save(communityEntity);
    }

    // 커뮤니티 특정 글 삭제
    public void communityDelete(Integer boNo) {
        communityRepository.deleteById(boNo);
    }
}
