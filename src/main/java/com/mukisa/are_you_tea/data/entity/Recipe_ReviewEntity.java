package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "Recipe_Review")
public class Recipe_ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Recipe_Review")
    private int recipe_review;

    @Column(name = "MB_ID")
    private String mb_id;
    @Column(name = "RECIPE_REVIEW_CONTENT")
    private String recipe_review_content;

    @Column(name = "RECIPE_REVIEW_REG_DATE")
    private String recipe_review_reg_date;

    @Column(name = "Recipe_NO")
    private int recipe_no;


}
