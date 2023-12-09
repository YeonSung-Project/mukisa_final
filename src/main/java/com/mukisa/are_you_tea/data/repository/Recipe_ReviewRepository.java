package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.Recipe_ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface Recipe_ReviewRepository extends JpaRepository<Recipe_ReviewEntity, Long> {

}
