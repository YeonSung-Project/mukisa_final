package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Efficacy")
public class EfficacyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EFFI_NO")
    private int effi_No;
    @Column(name = "EFFI" )
    private String effi;
    @Column(name = "EFFI_CONTENT")
    private String effi_content;

}
