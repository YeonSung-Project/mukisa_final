package com.mukisa.are_you_tea.service;


import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import com.mukisa.are_you_tea.data.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;


@Service
public class NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    SessionCheckService sessionCheckService;

    public Page<NoticeEntity> noticeList(Pageable pageable) {

        return noticeRepository.findAll(pageable);
    }

    public Page<NoticeEntity> noticeSearchList(String searchKeyword, Pageable pageable){

        return noticeRepository.findByNoTitleContaining(searchKeyword, pageable);
    }

    public void noticeWrite(NoticeEntity noticeEntity, MultipartFile file, String mbId) throws Exception {

        noticeEntity.setNoId(mbId);

        if (file != null && !file.isEmpty()) {


            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();



            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\communityFile"; // ???? ???
            File saveFile = new File(filePath, fileName);

            file.transferTo(saveFile);

            noticeEntity.setNoFilename(fileName);
            noticeEntity.setNoFilename("/noticeFile/" + fileName);

        }
        noticeRepository.save(noticeEntity);
    }

    public NoticeEntity noticeView(Integer noNo) {

        /** 글 번호 받아오기 */
        return noticeRepository.findById(noNo).get();
    }

    public void updateHits(Integer noNo) {
        NoticeEntity noticeEntity = noticeRepository.findById(noNo).orElse(null);
        if (noticeEntity != null) {
            noticeEntity.setNoHits(noticeEntity.getNoHits() + 1);
            noticeRepository.save(noticeEntity);
        }
    }


    public void noticeDelete(Integer noNo) {
        // 글 정보 가져오기
        NoticeEntity noticeEntity = noticeRepository.findById(noNo).orElse(null);

        // 글이 존재하면 처리
        if (noticeEntity != null) {
            // 파일이 존재하면 파일 삭제
            if (noticeEntity.getNoFilepath() != null && !noticeEntity.getNoFilepath().equals("파일이 없습니다.")) {
                String filePath = System.getProperty("user.dir") + File.separator + "src" +
                        File.separator + "main" + File.separator + "resources" +
                        File.separator + "static" + File.separator + "communityFile" +
                        File.separator + noticeEntity.getNoFilename();

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
            noticeRepository.deleteById(noNo);
        }
    }
}
