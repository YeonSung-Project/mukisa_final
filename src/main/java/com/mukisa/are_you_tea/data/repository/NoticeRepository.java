package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

    Page<NoticeEntity> findByNoTitleContaining(String searchKeyword, Pageable pageable);
}
