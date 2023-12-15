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
    private int TEA_NO;
    @Column(name = "TEA_CATEGORY" )
    private String TEA_CATEGORY;
    @Column(name = "TEA_NAME")
    private String TEA_NAME;
    @Column(name = "TEA_EXPLAIN")
    private String TEA_EXPLAIN;
    @Column(name = "TEA_RECIPE")
    private String TEA_RECIPE;
    @Column(name = "TEA_TASTE")
    private String TEA_TASTE;
    @Column(name = "TEA_IMPAT")
    private String TEA_IMPAT;
    @Column(name = "TEA_IMG")
    private String TEA_IMG;
}
