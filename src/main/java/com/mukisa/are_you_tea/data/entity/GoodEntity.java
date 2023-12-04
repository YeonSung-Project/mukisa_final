package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "GOOD")
public class GoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOOD_ID")
    private int good_id;
    @Column(name = "GOOD_CHECK" )
    private String good_check;
    @Column(name = "MB_ID")
    private String mb_id;
    @Column(name = "RECIPE_NO")
    private int recipe_no;

    private int good_cnt;




}
