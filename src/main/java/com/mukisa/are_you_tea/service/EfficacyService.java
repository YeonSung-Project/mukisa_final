package com.mukisa.are_you_tea.service;

import com.mukisa.are_you_tea.data.entity.EfficacyEntity;
import com.mukisa.are_you_tea.data.repository.EfficacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EfficacyService {

    @Autowired
    EfficacyRepository efficacyRepository;

    public List<EfficacyEntity> dataLoad() {
        Collections Collections = null;
        try {
            List<EfficacyEntity> efficacyEntities = efficacyRepository.findAll();

            // Comparator to sort by the "effi" column
            Comparator<EfficacyEntity> comparator = Comparator.comparing(EfficacyEntity::getEffi);

            // You can customize the comparator, for example, for descending order:
            // Comparator<EfficacyEntity> comparator = Comparator.comparing(EfficacyEntity::getEffi).reversed();

            Collections.sort(efficacyEntities, comparator);

            return efficacyEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}