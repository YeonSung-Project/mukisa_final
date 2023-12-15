package com.mukisa.are_you_tea.service;


import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import com.mukisa.are_you_tea.data.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    NoticeRepository noticeRepository;
    public Page<NoticeEntity> noticeList(Pageable pageable) {

        return noticeRepository.findAll(pageable);
    }

    public Page<NoticeEntity> communitySearchList(String searchKeyword, Pageable pageable){

        return noticeRepository.findByNoTitleContaining(searchKeyword, pageable);
    }
}
