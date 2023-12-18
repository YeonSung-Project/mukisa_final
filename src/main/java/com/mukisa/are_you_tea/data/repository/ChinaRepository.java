package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.ChinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChinaRepository extends JpaRepository<ChinaEntity, Integer> {

    // CTEA_CATEGORY로 DISTINCT 레시피 타입 조회
    @Query("SELECT DISTINCT c.cteaCategory FROM ChinaEntity c")
    List<String> findDistinctByCteaCategory();
    // CTEA_CATEGORY로 레시피 타입으로 레시피 조회

    List<ChinaEntity> findByCteaCategory(String recipeType);


}
