package com.mukisa.are_you_tea.data.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "account_user")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Column(name = "password", length = 256)
    private String password;
    private String email;
    private String role; // ROLE_USER, ROLE_ADMIN
    private String provider;
    private String providerId;
    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public UserEntity(String username, String password, String email, String role, String provider, String providerId,
                      Timestamp createDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }
}
