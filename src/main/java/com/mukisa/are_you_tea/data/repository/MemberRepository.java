package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    MemberEntity findByMbId(String mbId);
}
