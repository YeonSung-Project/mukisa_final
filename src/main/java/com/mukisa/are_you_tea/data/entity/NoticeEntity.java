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
        @Column(name = "NOTI_NO")         //�Խñ� ��ȣ
        private Integer noNo;
        @Column(name = "NOTI_WRITER")     // �ۼ���
        private String noId;
        @Column(name = "NOTI_TITLE")      // �Խñ� ����
        private String noTitle;
        @Column(name = "NOTI_CONTENT")    // �Խñ� ����
        private String noContent;
        @Column(name = "NOTI_FILENAME")   // ���� �̸�
        private String noFilename;
        @Column(name = "NOTI_FILEPATH")   // ���� ���
        private String noFilepath;
        @Column(name = "NOTI_DATE")       // �ۼ���
        private LocalDateTime noDate = LocalDateTime.now();
        @PrePersist
        protected void onCreate() {
            noDate = LocalDateTime.now();   // ���� ��¥�� �ð� �ޱ�
        }

        @Column(name = "NOTI_HITS")       // ��ȸ��
        private int noHits;

}
