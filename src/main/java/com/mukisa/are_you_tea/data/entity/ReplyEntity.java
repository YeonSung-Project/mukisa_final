package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @packageName    : com.mukisa.are_you_tea.data.entity
 * @fileName        : ReplyEntity
 * @author        : Youil Park
 * @date            : 2023-12-17
 * @description            :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-17      Youil Park       최초 생성
 */

@Entity
@Data
@Table(name="reply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="REP_NO")             // 댓글 번호
    private Integer repNo;
    @Column(name = "BO_NO")             // 게시판 번호
    private Integer boNo;
    @Column(name ="MB_ID")              // 댓글 작성자
    private String mbId;
    @Column(name ="REP_CONTENT")        // 댓글 내용
    private String repContent;
    @Column(name ="REP_DATE")           // 댓글 작성일
    private LocalDateTime repDate = LocalDateTime.now();
    @Column(name ="REP_MODY_DATE")      // 댓글 수정일
    private LocalDateTime repModyDate = LocalDateTime.now();

}
