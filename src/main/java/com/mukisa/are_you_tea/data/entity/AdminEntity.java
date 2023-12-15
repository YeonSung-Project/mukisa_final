package com.mukisa.are_you_tea.data.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "admin")
public class AdminEntity {

    @Id
    @Column(name = "id")
    String adId;
    @Column(name = "pw")
    String adPw;

    @Column(name = "role")
    String role;
}
