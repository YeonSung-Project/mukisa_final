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

    /**
     * @methodName : communityList
     * @description : 커뮤니티 글 리스트
     * @author  : Youil Park
     * @param : pageable                                페이징
     * @return : communityRepository.findAll(pageable)  페이지로 나눠진 커뮤니티 글 목록
     */
    public Page<CommunityEntity> communityList(Pageable pageable) {

        return communityRepository.findAll(pageable);
    }


    /**
     * @methodName : communityView
     * @description : 커뮤니티 특정 글 내용 보기
     * @author  : Youil Park
     * @param : boNo                                        글 번호
     * @return : communityRepository.findById(boNo).get()  특정 글의 내용
     */
    public CommunityEntity communityView(Integer boNo) {

        /** 글 번호 받아오기 */
        return communityRepository.findById(boNo).get();
    }

    /**
     * @methodName : communityWrite
     * @description : 커뮤니티 글 작성
     * @author  : Youil Park
     * @param : communityEntity
     * @param : file
     * @throws : Exception 파일 업로드 예외 처리
     */
    public void communityWrite(CommunityEntity communityEntity, MultipartFile file, String mbId) throws Exception {

        communityEntity.setMbId(mbId);

        if (file != null && !file.isEmpty()) {

            /** 식별자 */
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();  // 파일 식별자

            // ************* 파일 저장 *******************

            String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "communityFile"; // 파일 경로
            File saveFile = new File(filePath, fileName);

            file.transferTo(saveFile);

            communityEntity.setBoFilename(fileName);    // 파일이름
            communityEntity.setBoFilepath("/communityFile/" + fileName);    // 파일경로
            // ******************************************
        }   

        //communityEntity.setMbId(mbId); // 작성자 정보 설정
        communityRepository.save(communityEntity);
    }



    /**
     * @methodName : communityDelete
     * @description : 커뮤니티 특정 글 삭제
     * @author  : Youil Park
     * @param : boNo
     */
    public void communityDelete(Integer boNo) {
        // 글 정보 가져오기
        CommunityEntity communityEntity = communityRepository.findById(boNo).orElse(null);

        // 글이 존재하면 처리
        if (communityEntity != null) {
            // 파일이 존재하면 파일 삭제
            if (communityEntity.getBoFilepath() != null && !communityEntity.getBoFilepath().equals("파일이 없습니다.")) {
                String filePath = System.getProperty("user.dir") + File.separator + "src" +
                        File.separator + "main" + File.separator + "resources" +
                        File.separator + "static" + File.separator + "communityFile" +
                        File.separator + communityEntity.getBoFilename();

                File fileToDelete = new File(filePath);

                if (fileToDelete.exists() && fileToDelete.isFile()) {
                    if (fileToDelete.delete()) {
                        System.out.println("파일이 성공적으로 삭제되었습니다.");
                    } else {
                        System.err.println("파일 삭제 실패");
                    }
                }
            }

            // 글 삭제
            communityRepository.deleteById(boNo);
        }
    }


    /**
     * @methodName : communitySearchList
     * @description : 커뮤니티 검색 기능
     * @author  : Youil Park
     * @param : searchKeyword
     * @param : pageable
     * @return : communityRepository.findByBoTitleContaining(searchKeyword, pageable)   검색된 결과를 페이지로 나눠 반환
     */
    public Page<CommunityEntity> communitySearchList(String searchKeyword, Pageable pageable){

        return communityRepository.findByBoTitleContaining(searchKeyword, pageable);
    }

    /**
     * @methodName : updateHits
     * @description : 조회수
     * @author  : Youil Park
     * @param : boNo
     */
    public void updateHits(Integer boNo) {
        CommunityEntity community = communityRepository.findById(boNo).orElse(null);
        if (community != null) {
            community.setBoHits(community.getBoHits() + 1);
            communityRepository.save(community);
        }
    }
}
