package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "World")
public class WorldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORLD_NO")
    private int world_no;
    @Column(name = "WORLD" )
    private String world;

    @Column(name = "WORLD_SHARE")
    private float world_share;
    @Column(name = "WORLD_MAIN" )
    private String world_main;

    @Column(name = "WORLD_ABOVE" )
    private String world_above;

    @Column(name = "WORLD_HARVESTING" )
    private String world_harvesting;

    @Column(name = "WORLD_FAMOUS" )
    private String world_famous;

    @Column(name = "WORLD_IMG" )
    private String world_img;
}
