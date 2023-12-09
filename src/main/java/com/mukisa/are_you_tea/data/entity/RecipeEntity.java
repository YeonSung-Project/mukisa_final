package com.mukisa.are_you_tea.data.entity;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@Table(name = "RECIPE")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECIPE_NO")
    private int recipe_no;
    @Column(name = "RECIPE_name" )
    private String recipe_name;
    @Column(name = "RECIPE_quantity")
    private int recipe_quantity;
    @Column(name = "RECIPE_temperature")
    private int recipe_temperature;
    @Column(name = "RECIPE_time " )
    private String recipe_time ;
    @Column(name = "RECIPE_type")
    private String recipeType;
    @Column(name = "RECIPE_milk" )
    private String recipe_milk ;
    @Column(name = "RECIPE_content" )
    private String recipe_content ;
    @Column(name = "RECIPE_ingredient" )
    private String recipe_ingredient  ;
    @Column(name = "RECIPE_make" )
    private String recipe_make ;
    @Column(name = "RECIPE_img" )
    private String recipe_img ;
    @Column(name = "RECIPE_date" )
    private String recipe_date ;

    @Column(name = "RECIPE_mdf_date" )
    private String recipe_mdf_date ;


}
