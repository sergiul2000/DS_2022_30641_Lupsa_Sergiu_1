package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.controllers;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.UsersDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services.UsersService;
//import jdk.internal.net.http.common.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService){
        this.usersService=usersService;
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
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody UsersDTO usersDTO) {
        UUID usersId = usersService.insert(usersDTO);
        return new ResponseEntity<>(usersId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> getUsersById(@PathVariable("id") UUID userId) {
        UsersDTO dto = usersService.findUserById(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
//    @GetMapping(value = "/{username}")
//    public ResponseEntity<UsersDTO> getUsersByUsername(@PathVariable("username") UUID userId) {
//        UsersDTO dto = usersService.findUserByU(userId);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }

    @DeleteMapping(value = "/{id}")
    public void deleteUsers(@PathVariable("id") UUID userId) {
        usersService.delete(userId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UUID> updateProsumer(@PathVariable("id") UUID userId, @Valid @RequestBody UsersDTO usersDTO) {
        UUID usersId = usersService.updateUser(userId,usersDTO);
        return new ResponseEntity<>(usersId, HttpStatus.OK);
    }   
}
