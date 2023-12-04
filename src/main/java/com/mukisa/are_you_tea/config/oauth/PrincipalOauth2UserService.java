package com.mukisa.are_you_tea.config.oauth;

import com.mukisa.are_you_tea.config.auth.PrincipalDetails;
import com.mukisa.are_you_tea.config.oauth.provider.*;
import com.mukisa.are_you_tea.data.entity.UserEntity;
import com.mukisa.are_you_tea.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    HttpSession httpSession;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // userRequest 확인
        System.out.println("userRequest = " + userRequest);
        System.out.println("getClientRegistration = " + userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인 하는지 알 수 있음
        System.out.println("getAccessToken = " + userRequest.getAccessToken());
        System.out.println("getAttributes = " + super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = null;
        if (registrationId.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(attributes);
        } else if (registrationId.equals("facebook")) {
            oAuth2UserInfo = new FacebookUserInfo(attributes);
        } else if (registrationId.equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo((Map)attributes.get("response"));
        } else if (registrationId.equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(attributes);
        } else{
            System.out.println("구글, 페이스북, 네이버, 카카오 로그인만 지원합니다.");
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";

        UserEntity findUser = userRepository.findByUsername(username);
        if(findUser == null) {
            findUser = UserEntity.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(findUser);
        }
        httpSession.setAttribute("userSession", username);
        System.out.println("로그인 정보 세션에 저장" + httpSession.getAttribute("userSession"));
        return new PrincipalDetails(findUser, oAuth2User.getAttributes());
    }
}
