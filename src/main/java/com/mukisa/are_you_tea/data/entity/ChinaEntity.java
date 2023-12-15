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
    private int CTEA_NO;
    @Column(name = "CTEA_CATEGORY" )
    private String CTEA_CATEGORY;
    @Column(name = "CTEA_NAME")
    private String CTEA_NAME;
    @Column(name = "CTEA_EXPLAIN")
    private String CTEA_EXPLAIN;
    @Column(name = "CTEA_RECIPE")
    private String CTEA_RECIPE;
    @Column(name = "CTEA_TASTE")
    private String CTEA_TASTE;
    @Column(name = "CTEA_IMPAT")
    private String CTEA_IMPAT;
    @Column(name = "CTEA_IMG")
    private String CTEA_IMG;
}
