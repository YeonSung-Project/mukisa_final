package com.mukisa.are_you_tea.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Dictionary")
public class DictionaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DICTIONARY_NO")
    private int dictionary_no;
    @Column(name = "DICTIONARY_NAME" )
    private String dictionary_name;
    @Column(name = "DICTIONARY_CONTENT")
    private String dictionary_content;

}
