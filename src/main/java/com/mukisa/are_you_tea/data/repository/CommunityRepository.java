package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity,Integer>{

    // 커뮤니티 검색 기능
    Page<CommunityEntity> findByBoTitleContaining(String searchKeyword, Pageable pageable);

}
