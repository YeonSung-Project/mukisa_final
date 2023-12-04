package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.GoodEntity;
import com.mukisa.are_you_tea.data.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {

    @Autowired
    GoodRepository goodRepository;


}
