package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.DeviceDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Device;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeviceBuilder {
    public static DeviceDTO toDeviceDTO(Device device){
        return new DeviceDTO(device.getId(),device.getName(),device.getSerial_nr(),device.getConsumption(),device.getConsumptionList());
    }

    public static Device toEntity(DeviceDTO deviceDTO) {
        return new Device(deviceDTO.getId(),
                deviceDTO.getName(),
                deviceDTO.getSerial_nr(),
                deviceDTO.getConsumtion(),
                deviceDTO.getConsumptionList());
    }
}
