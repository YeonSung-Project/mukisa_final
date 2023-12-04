package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.GoodEntity;
import com.mukisa.are_you_tea.data.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodService {

    private final GoodRepository goodRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }


}
