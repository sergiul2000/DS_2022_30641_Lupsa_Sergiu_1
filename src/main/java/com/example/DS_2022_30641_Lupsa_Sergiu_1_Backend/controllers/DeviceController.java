package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.controllers;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.ConsumptionDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.DeviceDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){
        this.deviceService=deviceService;
    }

    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices(){
        List<DeviceDTO> dtos = deviceService.findDevices();
        for (DeviceDTO dto : dtos) {
            Link deviceLink = linkTo(methodOn(DeviceController.class)
                    .getDevices(dto.getId())).withRel("deviceDetails");
            dto.add(deviceLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody DeviceDTO deviceDTO) {
        UUID deviceId = deviceService.insert(deviceDTO);
        return new ResponseEntity<>(deviceId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> getDevices(@PathVariable("id") UUID deviceId) {
        DeviceDTO dto = deviceService.findDeviceById(deviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDevice(@PathVariable("id") UUID deviceId) {
        deviceService.delete(deviceId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UUID> updateProsumer(@PathVariable("id") UUID deviceId, @Valid @RequestBody DeviceDTO deviceDTO) {
        deviceId = deviceService.updateDevice(deviceId,deviceDTO);
        return new ResponseEntity<>(deviceId, HttpStatus.OK);
    }

    @GetMapping(value = "/getDeviceConsumptions/{id}")
    public ResponseEntity<List<ConsumptionDTO>> getDeviceConsumtions(@PathVariable("id") UUID id){
        List<ConsumptionDTO> listaConsumption = deviceService.getDeviceConsumtions(id);
        if(listaConsumption.size()!=0)
            return new ResponseEntity<>(listaConsumption,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
        @PutMapping(value = "/addConsumptionToDevice/{deviceId}")
    public ResponseEntity<Boolean> addConsumptionToDevice(@PathVariable UUID deviceId,@RequestBody  ConsumptionDTO consumptionDTO)
    {
        boolean response = deviceService.addConsumptionToDevice(deviceId,consumptionDTO);
        if(response)
            return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
