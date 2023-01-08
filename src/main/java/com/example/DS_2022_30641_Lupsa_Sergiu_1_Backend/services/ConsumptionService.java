package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.ConsumptionDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.DeviceDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders.ConsumptionBuilder;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders.DeviceBuilder;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Consumption;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Device;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Users;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.repositories.ConsumptionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsumptionService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumptionService.class);
    private final ConsumptionRepo consumptionRepo;


    @Autowired
    public ConsumptionService(ConsumptionRepo consumptionRepo) {
        this.consumptionRepo = consumptionRepo;
    }


    public List<ConsumptionDTO> findConsumptions(){
        List<Consumption> consumptionList = consumptionRepo.findAll();
        return consumptionList.stream()
                .map(ConsumptionBuilder::toConsumptionDTO)
                .collect(Collectors.toList());
    }

    public ConsumptionDTO findConsumptionById(UUID id) {
        Optional<Consumption> prosumerOptional = consumptionRepo.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Consumption with id {} was not found in db", id);
            throw new ResourceNotFoundException(Consumption.class.getSimpleName() + " with id: " + id);
        }
        return ConsumptionBuilder.toConsumptionDTO(prosumerOptional.get());
    }

    public UUID insert(ConsumptionDTO consumptionDTO) {
        Consumption consumption = ConsumptionBuilder.toEntity(consumptionDTO);
        consumption = consumptionRepo.save(consumption);
        LOGGER.debug("Consumption with id {} was inserted in db", consumption.getId());
        return consumption.getId();
    }

    public void delete(UUID id) {
        consumptionRepo.deleteById(id);
    }

    public UUID updateConsumption(UUID id,ConsumptionDTO consumptionDTO){
        Consumption consumption = consumptionRepo.findById(id).orElseThrow(RuntimeException::new);
        consumption.setValue(consumptionDTO.getValue());
        consumption.setTimestamp(consumptionDTO.getTimestamp());
        consumptionRepo.save(consumption);

        return consumption.getId();
    }


}
