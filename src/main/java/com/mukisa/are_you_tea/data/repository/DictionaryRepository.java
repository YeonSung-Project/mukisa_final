package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.DictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Integer> {


}
