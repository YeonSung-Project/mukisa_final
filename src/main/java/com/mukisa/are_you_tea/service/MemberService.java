package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.AdminEntity;
import com.mukisa.are_you_tea.data.entity.MemberEntity;
import com.mukisa.are_you_tea.data.repository.AdminRepository;
import com.mukisa.are_you_tea.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AdminRepository adminRepository;

    public boolean login(String mbId, String mbPw) {
        try {
            MemberEntity memberEntity = memberRepository.findByMbId(mbId);
            AdminEntity adminEntity = adminRepository.findByAdId(mbId);
            if (memberEntity != null && memberEntity.getMbPw().equals(mbPw) || adminEntity != null && adminEntity.getAdPw().equals(mbPw)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public int signup(String mbId, String mbPw, String mbNm) {
        try {
            // 비밀번호 유효성 검사
            String pwRegex = "^[A-Za-z0-9!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~\\\\\\\\]{8,16}$";
            Pattern pwPattern = Pattern.compile(pwRegex);
            Matcher pwMatcher = pwPattern.matcher(mbPw);
            if (!pwMatcher.matches()) {
                return 3;  // 비밀번호 양식 오류
            }

            MemberEntity member = memberRepository.findByMbId(mbId);
            AdminEntity admin = adminRepository.findByAdId(mbId);
            AdminEntity admin_matcher = adminRepository.findByAdId(mbNm);
            if(admin != null){
                return 4; // 관리자와 아이디가 겹침
            }
            if(admin_matcher != null){ // 관리자와 닉네임이 겹침
                return 5;
            }
            if (member != null) {
                return 0;  // 아이디 중복
            } else {
                MemberEntity newMember = new MemberEntity();
                newMember.setMbId(mbId);
                newMember.setMbPw(mbPw);
                newMember.setMbNm(mbNm);
                newMember.setRole("ROLE_USER");
                Date now = new Date();
                newMember.setMbDate(now);
                memberRepository.save(newMember);
                return 1;  // 회원가입 성공
            }
        } catch (Exception e) {
            return 2;  // 회원가입 실패
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
