package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {


    // TEA_CATEGORY로 DISTINCT 레시피 타입 조회
    @Query("SELECT DISTINCT m.teaCategory FROM MedicineEntity m")
    List<String> findDistinctByTeaCategory();
    // TEA_CATEGORY로 레시피 타입으로 레시피 조회

    List<MedicineEntity> findByTeaCategory(String recipeType);


}
