package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MB_NO")
    private int mbNo;
    @Column(name = "MB_ID", unique = true)
    private String mbId;
    @Column(name = "MB_PW")
    private String mbPw;
    @Column(name = "MB_NM")
    private String mbNm;
    @Column(name = "MB_DATE")
    private Date mbDate;
    @Column(name = "MB_YN")
    private String mbYn = "N";
    @Column(name = "MB_NDATE")
    private Date mbNdate;
}
