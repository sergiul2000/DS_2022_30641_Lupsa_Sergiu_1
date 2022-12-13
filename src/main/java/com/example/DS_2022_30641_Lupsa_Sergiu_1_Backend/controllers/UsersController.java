package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.controllers;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.ConsumptionDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.DeviceDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.LoginDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.UsersDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Users;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services.DeviceService;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services.UsersService;
//import jdk.internal.net.http.common.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UsersController {

    private final UsersService usersService;
    private final DeviceService deviceService;

    @Autowired
    public UsersController(UsersService usersService,DeviceService deviceService){
        this.usersService=usersService;
        this.deviceService=deviceService;
    }

    @GetMapping()
    public ResponseEntity<List<UsersDTO>> getUsers(){
        List<UsersDTO> dtos = usersService.findUsers();
        System.out.println(dtos.size());
//        for (UsersDTO dto : dtos) {
//            Link usersLink = linkTo(methodOn(UsersController.class)
//                    .getUsers(dto.getId())).withRel("usersDetails");
//            dto.add(usersLink);
//        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()//(value = "/insert")
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody UsersDTO usersDTO){
        UUID usersId = usersService.insert(usersDTO);
        if(usersId==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usersId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> getUsersById(@PathVariable("id") UUID userId) {
        UsersDTO dto = usersService.findUserById(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping(value = "/username/{username}")
    public ResponseEntity<UsersDTO> getUsersByUsername(@PathVariable("username") String username) {
        UsersDTO dto = usersService.findUserByUsername(username);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUsers(@PathVariable("id") UUID userId) {
        usersService.delete(userId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UUID> updateProsumer(@PathVariable("id") UUID userId, @Valid @RequestBody UsersDTO usersDTO) {
        userId = usersService.updateUser(userId,usersDTO);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UsersDTO> login(@RequestBody LoginDTO loginDTO) {
        UsersDTO users = usersService.loginFunction(loginDTO.getUsername(),loginDTO.getPassword());
        if(users==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping(value = "/getClientDevices/{id}")
    public ResponseEntity<List<DeviceDTO>> getClientDevices(@PathVariable("id") UUID id){
        List<DeviceDTO> listaDevices = usersService.getClientDevices(id);
        if(listaDevices.size()!=0)
            return new ResponseEntity<>(listaDevices,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/getClientConsumtions/{id}")
    public ResponseEntity<List<ConsumptionDTO>> getClientConsumtions(@PathVariable("id") UUID id){
        List<ConsumptionDTO> listaConsumptionUser=new ArrayList<ConsumptionDTO>();
        List<DeviceDTO> listaDevices = usersService.getClientDevices(id);
        if(listaDevices.size()!=0){
            for(DeviceDTO deviceDTO:listaDevices){

                List<ConsumptionDTO> listaConsumption = deviceService.getDeviceConsumtions(deviceDTO.getId());
                if(listaConsumption.size()!=0){
                    listaConsumptionUser.addAll(listaConsumption);
                }
            }
            return new ResponseEntity<>(listaConsumptionUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

//    @PostMapping(value = "/addDeviceToClient")
//    public ResponseEntity<UUID> addDeviceToClient(@PathVariable String username,@PathVariable  UUID deviceId){
//        Optional<Users> usersDTO = usersService.findUserByUsername(username);
//        UUID deviceId = deviceService.insert(deviceDTO);
////        usersDTO
//        return new ResponseEntity<>(deviceId, HttpStatus.CREATED);
//    }

    @PutMapping(value = "/addDeviceToClient/{clientId}")
    public ResponseEntity<Boolean> addDeviceToClient(@PathVariable UUID clientId,@RequestBody  DeviceDTO deviceDTO)
    {
        boolean response = usersService.addDeviceToClient(clientId,deviceDTO);
        if(response)
            return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
