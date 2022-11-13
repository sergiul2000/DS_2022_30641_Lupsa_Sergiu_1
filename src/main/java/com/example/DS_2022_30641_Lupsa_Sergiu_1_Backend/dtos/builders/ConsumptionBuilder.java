package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.ConsumptionDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.DeviceDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Consumption;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Device;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConsumptionBuilder {
    public static ConsumptionDTO toConsumptionDTO(Consumption consumption){
        return new ConsumptionDTO(consumption.getId(),consumption.getValue());
    }
    public static Consumption toEntity(ConsumptionDTO consumptionDTO) {
        return new Consumption(consumptionDTO.getId(),
                consumptionDTO.getValue());
    }
}
