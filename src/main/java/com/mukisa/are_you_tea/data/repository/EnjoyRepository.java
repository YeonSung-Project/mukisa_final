package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.EnjoyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnjoyRepository extends JpaRepository<EnjoyEntity, Integer> {



}
