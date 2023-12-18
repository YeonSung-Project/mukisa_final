package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "China")
public class ChinaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTEA_NO")
    private int ctea_no;
    @Column(name = "CTEA_category" )
    private String cteaCategory;
    @Column(name = "CTEA_NAME")
    private String ctea_name;
    @Column(name = "CTEA_EXPLAIN")
    private String ctea_explain;
    @Column(name = "CTEA_RECIPE")
    private String ctea_recipe;
    @Column(name = "CTEA_TASTE")
    private String ctea_taste;
    @Column(name = "CTEA_IMPAT")
    private String ctea_impat;
    @Column(name = "CTEA_IMG")
    private String ctea_img;
}
