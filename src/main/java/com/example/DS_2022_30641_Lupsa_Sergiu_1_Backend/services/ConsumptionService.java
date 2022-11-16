package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.repositories.ConsumptionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ConsumptionService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumptionService.class);
    private final ConsumptionRepo consumptionRepo;


    @Autowired
    public ConsumptionService(ConsumptionRepo consumptionRepo) {
        this.consumptionRepo = consumptionRepo;
    }


}
