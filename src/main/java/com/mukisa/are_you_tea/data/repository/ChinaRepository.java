package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.ChinaEntity;
import com.mukisa.are_you_tea.data.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChinaRepository extends JpaRepository<ChinaEntity, Integer> {


}
