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
        @Column(name = "NOTI_NO")         //???? ???
        private Integer noNo;
        @Column(name = "NOTI_WRITER")     // ?????
        private String noId;
        @Column(name = "NOTI_TITLE")      // ???? ????
        private String noTitle;
        @Column(name = "NOTI_CONTENT")    // ???? ????
        private String noContent;
        @Column(name = "NOTI_FILENAME")   // ???? ???
        private String noFilename;
        @Column(name = "NOTI_FILEPATH")   // ???? ???
        private String noFilepath;
        @Column(name = "NOTI_DATE")       // ?????
        private LocalDateTime noDate = LocalDateTime.now();
        @PrePersist
        protected void onCreate() {
                noDate = LocalDateTime.now();   // ???? ????? ?©£? ???
        }

        @Column(name = "NOTI_HITS")       // ?????
        private int noHits;

        public void setNoFilename(String fileName) {
        }
}
