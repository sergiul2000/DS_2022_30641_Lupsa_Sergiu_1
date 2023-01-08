package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.ConsumptionDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.DeviceDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders.ConsumptionBuilder;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders.DeviceBuilder;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Consumption;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Device;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Users;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.repositories.ConsumptionRepo;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.repositories.DeviceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepo deviceRepo;
    private final ConsumptionRepo consumptionRepo;


    @Autowired
    public DeviceService(DeviceRepo deviceRepo, ConsumptionRepo consumptionRepo) {
        this.deviceRepo = deviceRepo;
        this.consumptionRepo = consumptionRepo;
    }

    public List<DeviceDTO> findDevices(){
        List<Device> deviceList = deviceRepo.findAll();
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO findDeviceById(UUID id) {
        Optional<Device> prosumerOptional = deviceRepo.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toDeviceDTO(prosumerOptional.get());
    }

    public UUID insert(DeviceDTO deviceDTO) {

        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepo.save(device);
        LOGGER.debug("Device with id {} was inserted in db", device.getId());
        return device.getId();
    }

    public void delete(UUID id) {
        deviceRepo.deleteById(id);
    }

    public UUID updateDevice(UUID id,DeviceDTO deviceDTO){
        Device device = deviceRepo.findById(id).orElseThrow(RuntimeException::new);
        device.setDescription(deviceDTO.getDescription());
        device.setAddress(deviceDTO.getAddress());
        device.setMaxim_hourly_energy(deviceDTO.getMaxim_hourly_energy());
        deviceRepo.save(device);

        return device.getId();
    }

    public List<ConsumptionDTO> getDeviceConsumtions(UUID deviceID){
        Optional<Device> deviceOptional = deviceRepo.findById(deviceID);
        if(!deviceOptional.isPresent()) return null;
        List<ConsumptionDTO> listaConsumption=new ArrayList<>();
        Device device = deviceOptional.get();
        for (int i=0;i<device.getConsumptionList().size();i++){
            listaConsumption.add(i, ConsumptionBuilder.toConsumptionDTO(device.getConsumptionList().get(i)));
        }
        return listaConsumption;
    }

    public Boolean addConsumptionToDevice(UUID deviceId, ConsumptionDTO consumptionDTO){
        Optional<Device> deviceOptional = deviceRepo.findById(deviceId);
        Consumption consumption = ConsumptionBuilder.toEntity(consumptionDTO);
        consumption = consumptionRepo.save(consumption);
        LOGGER.debug("Consumption with id {} was inserted in db", consumption.getId());
        if(!deviceOptional.isPresent() ) return false;

        Device device = deviceOptional.get();

        device.getConsumptionList().add(consumption);
        deviceRepo.save(device);
        return true;
    }

    public Boolean createConsumptionFromRabbitMQ(String msg){
        System.out.println(msg);
        String arr[] = msg.split(":\"");

        //cod doar pentru a intelege ce e aici
        int index=0;
        for(String  c:arr){
            System.out.println(c+" ,index= "+index);
            index++;
        }

        //getting measured value from message
        String measuredValueSplit[] = arr[1].split("\"");
        String measuredValueString = measuredValueSplit[0];
        Float measuredValue= Float.parseFloat(measuredValueString);
        System.out.println("measured value is "+measuredValue);

        //getting device's id value from message
        String deviceIdSplit[] = arr[2].split("\"");
        String deviceIdString = deviceIdSplit[0];
        UUID deviceId= UUID.fromString(deviceIdString);
        System.out.println("UUID is "+deviceId);

        //getting timestamp value from message
        String timestampSplit[] = arr[3].split("\"");
        String timestampString = timestampSplit[0];
        int year=Integer.parseInt(timestampString.substring(0,4));
        int month=Integer.parseInt(timestampString.substring(5,7));
        int day=Integer.parseInt(timestampString.substring(8,10));
//        System.out.println(year+""+month+""+day);

        int hour=Integer.parseInt(timestampString.substring(11,13));
        int min=Integer.parseInt(timestampString.substring(14,16));
        int sec=Integer.parseInt(timestampString.substring(17,19));

//        System.out.println(hour+""+min+""+sec);

        LocalDateTime timestamp= LocalDateTime.of(year,month,day,hour,min,sec);
//        System.out.println(timestamp);
        System.out.println("Timestamp is "+timestamp);
//        System.out.println(new ConsumptionDTO(measuredValue,timestamp).getId());
        Boolean returnValue=addConsumptionToDevice(deviceId,new ConsumptionDTO(measuredValue,timestamp));
//        Boolean returnValue=true;
        return returnValue;
    }

}
