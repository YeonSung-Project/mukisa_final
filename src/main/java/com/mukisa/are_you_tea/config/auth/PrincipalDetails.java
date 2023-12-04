package com.mukisa.are_you_tea.config.auth;


import com.mukisa.are_you_tea.data.entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private UserEntity userEntity;
    private Map<String, Object> attributes;

    //일반 로그인
    public PrincipalDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    // OAuth 로그인
    public PrincipalDetails(UserEntity userEntity, Map<String, Object> attributes){
        this.userEntity = userEntity;
        this.attributes = attributes;
    }

    // 해당 User의 권한을 Return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        // 1년동안 로그인 하지 않은 경우 => 휴먼계정으로 하기

        return true;
    }

    // OAuth Override
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
