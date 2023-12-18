package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Medicine_tea")
public class MedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEA_NO")
    private int tea_no;
    @Column(name = "TEA_CATEGORY" )
    private String teaCategory;
    @Column(name = "TEA_NAME")
    private String tea_name;
    @Column(name = "TEA_EXPLAIN")
    private String tea_explain;
    @Column(name = "TEA_RECIPE")
    private String tea_recipe;
    @Column(name = "TEA_TASTE")
    private String tea_taste;
    @Column(name = "TEA_IMPACT")
    private String tea_impact;
    @Column(name = "TEA_IMG")
    private String tea_img;
}
