package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.repository.CommunityRepository;
import com.mukisa.are_you_tea.data.repository.EnjoyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @packageName    : com.mukisa.are_you_tea.service
 * @fileName        : CommunityService
 * @author        : Youil Park
 * @date            : 2023-11-26
 * @description            :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-26      Youil Park       최초 생성
 */

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    // 커뮤니티 글 리스트
    public Page<CommunityEntity> communityList(Pageable pageable) {

        return communityRepository.findAll(pageable);
    }

    // 커뮤니티 특정 글 내용 보기
    public CommunityEntity communityView(Integer boNo) {

        // 글 번호 받아오기
        return communityRepository.findById(boNo).get();
    }

    // 커뮤니티 글 작성
    public void communityWrite(CommunityEntity communityEntity, MultipartFile file) throws Exception {
        //String mbId = communityEntity.getMbId(); // 작성자 정보 가져오기

        if (file != null && !file.isEmpty()) {

            // 식별자
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();  // 파일 식별자

            // ************* 파일 저장 *******************

            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\communityFile"; // 파일 경로
            File saveFile = new File(filePath, fileName);

            file.transferTo(saveFile);

            communityEntity.setBoFilename(fileName);    // 파일이름
            communityEntity.setBoFilepath("/communityFile/" + fileName);    // 파일경로
            // ******************************************
        }

        //communityEntity.setMbId(mbId); // 작성자 정보 설정
        communityRepository.save(communityEntity);
    }


    // 커뮤니티 특정 글 삭제
    public void communityDelete(Integer boNo) {
        communityRepository.deleteById(boNo);
    }

    // 커뮤니티 검색 기능
    public Page<CommunityEntity> communitySearchList(String searchKeyword, Pageable pageable){

        return communityRepository.findByBoTitleContaining(searchKeyword, pageable);
    }

    // 조회수
    public void updateHits(Integer boNo) {
        CommunityEntity community = communityRepository.findById(boNo).orElse(null);
        if (community != null) {
            community.setBoHits(community.getBoHits() + 1);
            communityRepository.save(community);
        }
    }
}
