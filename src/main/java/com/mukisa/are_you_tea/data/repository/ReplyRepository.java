package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @packageName    : com.mukisa.are_you_tea.data.repository
 * @fileName        : ReplyRepository
 * @author        : Youil Park
 * @date            : 2023-12-17
 * @description            :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-17      Youil Park       최초 생성
 */

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity,Integer> {

}
