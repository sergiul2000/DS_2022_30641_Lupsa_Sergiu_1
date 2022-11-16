package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.DeviceDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Consumption;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Device;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class DeviceBuilder {
    public static DeviceDTO toDeviceDTO(Device device){
        return new DeviceDTO(device.getId(),device.getAddress(),device.getDescription(),device.getMaxim_hourly_energy(),device.getConsumptionList());
    }

    public static Device toEntity(DeviceDTO deviceDTO) {
        return new Device(deviceDTO.getId(),
                deviceDTO.getAddress(),
                deviceDTO.getDescription(),
                deviceDTO.getMaxim_hourly_energy(),
                new ArrayList<Consumption>()
        );
    }
}
