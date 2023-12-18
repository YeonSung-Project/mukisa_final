package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String> {

    AdminEntity findByAdId(String id);
}
