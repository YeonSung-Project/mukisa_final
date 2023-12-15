package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {




}
