package com.mukisa.are_you_tea.config;

import com.mukisa.are_you_tea.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/")
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/");
                    }
                });

        return httpSecurity.build();
    }

}
