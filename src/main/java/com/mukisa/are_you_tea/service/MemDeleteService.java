package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.MemberEntity;
import com.mukisa.are_you_tea.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MemDeleteService {
    @Autowired
    MemberRepository memberRepository;
    public void memDelService(String mbId){
        MemberEntity memberEntity = memberRepository.findByMbId(mbId);
        if(memberEntity != null){
            memberRepository.deleteById(memberEntity.getMbNo());
        }
    }
}
