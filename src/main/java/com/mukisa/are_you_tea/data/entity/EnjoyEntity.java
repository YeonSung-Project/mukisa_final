package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ENJOY")
public class EnjoyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENJOY_NO")
    private int enjoy_no;
    @Column(name = "Enjoy_TYPE" )
    private String enjoy_type;
    @Column(name = "Enjoy_CONTENT" )
    private String enjoy_content;
    @Column(name = "Enjoy_DRYLEAF" )
    private String enjoy_dryleaf;
    @Column(name = "Enjoy_WETLEAF" )
    private String enjoy_wetleaf;
    @Column(name = "Enjoy_WATER" )
    private String enjoy_water;
    @Column(name = "Enjoy_USED" )
    private String enjoy_used;
    @Column(name = "Enjoy_amount" )
    private String enjoy_amount;
    @Column(name = "Enjoy_temperature" )
    private String enjoy_temperature;
    @Column(name = "Enjoy_teatime" )
    private String enjoy_teatime;
    @Column(name = "Enjoy_dryleaf_img" )
    private String enjoy_dryleaf_img;
    @Column(name = "Enjoy_wetleaf_img" )
    private String enjoy_wetleaf_img;
    @Column(name = "Enjoy_water_img" )
    private String enjoy_water_img;


}
