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

    public Page<NoticeEntity> communitySearchList(String searchKeyword, Pageable pageable){

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
}
