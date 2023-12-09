package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @packageName    : com.mukisa.are_you_tea.data.entity
 * @fileName        : CommunityEntity
 * @author        : Youil Park
 * @date            : 2023-11-26
 * @description            :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-26      Youil Park       최초 생성
 */

@Entity
@Data
@Table(name = "community")
// communityT : 테스트 게시판 DB
// community : 실제 사용하는 게시판 DB
public class CommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "BO_NO")         //게시글 번호
    private Integer boNo;
    @Column(name = "MB_ID")         // 작성자
    private String mbId = "Test";
    @Column(name = "BO_TITLE")      // 게시글 제목
    private String boTitle;
    @Column(name = "BO_CONTENT")    // 게시글 내용
    private String boContent;
    @Column(name = "BO_FILENAME")   // 파일 이름
    private String boFilename;
    @Column(name = "BO_FILEPATH")   // 파일 경로
    private String boFilepath;
    @Column(name = "BO_DATE")       // 작성일
    private LocalDateTime boDate = LocalDateTime.now();
    @PrePersist
    protected void onCreate() {
        boDate = LocalDateTime.now();   // 현재 날짜와 시간 받기
    }

    @Column(name = "BO_HITS")       // 조회수
    private int boHits;
}
