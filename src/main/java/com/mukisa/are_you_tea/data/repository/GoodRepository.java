package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<GoodEntity, Integer> {


}
