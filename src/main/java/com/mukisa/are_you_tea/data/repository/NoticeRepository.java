package com.mukisa.are_you_tea.data.repository;

import com.mukisa.are_you_tea.data.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

    // 살려주세요 JPQL이 뭔가요 학교에선 SQL만 알려줬는데
    //@Query(value =
    //        "SELECT n FROM NoticeEntity notice" +
    //                "WHERE " +
    //                "noId LIKE %:searchKeyword% OR " +
    //                "noTitle LIKE %:searchKeyword% OR " +
    //                "noContent LIKE %:searchKeyword%")
    //Page<NoticeEntity> findByNoTitleContaining(@Param("searchKeyword") String searchKeyword, Pageable pageable);

    Page<NoticeEntity> findByNoTitleContaining(String searchKeyword, Pageable pageable);
}
