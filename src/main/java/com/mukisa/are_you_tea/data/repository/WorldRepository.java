package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.WorldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldRepository extends JpaRepository<WorldEntity, Integer> {



}
