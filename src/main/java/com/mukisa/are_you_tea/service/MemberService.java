package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.MemberEntity;
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

    public boolean login(String mbId, String mbPw) {
        try {
            MemberEntity memberEntity = memberRepository.findByMbId(mbId);
            if (memberEntity != null && memberEntity.getMbPw().equals(mbPw)) {
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
            // ��й�ȣ ��ȿ�� �˻�
            String pwRegex = "^[A-Za-z0-9!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~\\\\\\\\]{8,16}$";
            Pattern pwPattern = Pattern.compile(pwRegex);
            Matcher pwMatcher = pwPattern.matcher(mbPw);
            if (!pwMatcher.matches()) {
                return 3;  // ��й�ȣ ��� ����
            }

            MemberEntity member = memberRepository.findByMbId(mbId);
            if (member != null) {
                return 0;  // ���̵� �ߺ�
            } else {
                MemberEntity newMember = new MemberEntity();
                newMember.setMbId(mbId);
                newMember.setMbPw(mbPw);
                newMember.setMbNm(mbNm);
                newMember.setRole("USER");
                Date now = new Date();
                newMember.setMbDate(now);
                memberRepository.save(newMember);
                return 1;  // ȸ������ ����
            }
        } catch (Exception e) {
            return 2;  // ȸ������ ����
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
