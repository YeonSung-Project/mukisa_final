package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.ChinaEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ChinaRepository extends JpaRepository<ChinaEntity, Integer> {




}
