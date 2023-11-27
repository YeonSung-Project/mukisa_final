package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "communityT")
// communityT : 테스트 게시판 DB
// community : 실제 사용하는 게시판 DB
public class CommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "BO_NO")         //게시글 번호
    private Integer boNo;
    @Column(name = "BO_TITLE")      // 게시글 제목
    private String boTitle;
    @Column(name = "BO_CONTENT")    // 게시글 내용
    private String boContent;
}
