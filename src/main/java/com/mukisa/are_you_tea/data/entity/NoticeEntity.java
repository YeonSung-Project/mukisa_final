package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notice")
public class NoticeEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "NOTI_NO")         //게시글 번호
        private Integer noNo;
        @Column(name = "NOTI_WRITER")     // 작성자
        private String noId;
        @Column(name = "NOTI_TITLE")      // 게시글 제목
        private String noTitle;
        @Column(name = "NOTI_CONTENT")    // 게시글 내용
        private String noContent;
        @Column(name = "NOTI_FILENAME")   // 파일 이름
        private String noFilename;
        @Column(name = "NOTI_FILEPATH")   // 파일 경로
        private String noFilepath;
        @Column(name = "NOTI_DATE")       // 작성일
        private LocalDateTime noDate = LocalDateTime.now();
        @PrePersist
        protected void onCreate() {
            noDate = LocalDateTime.now();   // 현재 날짜와 시간 받기
        }

        @Column(name = "NOTI_HITS")       // 조회수
        private int noHits;

}
