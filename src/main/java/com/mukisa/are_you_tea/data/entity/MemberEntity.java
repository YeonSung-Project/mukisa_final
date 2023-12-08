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
    private int mbNo;           // 회원 고유 번호
    @Column(name = "MB_ID", unique = true)
    private String mbId;        // 회원 아이디
    @Column(name = "MB_PW")
    private String mbPw;        // 회원 비밀번호
    @Column(name = "MB_NM")
    private String mbNm;        // 회원 닉네임
    @Column(name="MB_ROLE")
    private String role; // ROLE_USER, ROLE_ADMIN
    @Column(name = "MB_DATE")
    private Date mbDate;        // 회원 가입일
    @Column(name = "MB_YN")
    private String mbYn = "N";  // 회원 탈퇴 여부
    @Column(name = "MB_NDATE")
    private Date mbNdate;       // 회원 탈퇴일자
}
