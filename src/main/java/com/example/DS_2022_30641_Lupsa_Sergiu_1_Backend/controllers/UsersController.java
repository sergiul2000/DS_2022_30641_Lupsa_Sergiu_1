package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.controllers;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.UsersDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
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
        for (UsersDTO dto : dtos) {
            Link usersLink = linkTo(methodOn(UsersController.class)
                    .getUsers(dto.getId())).withRel("usersDetails");
            dto.add(usersLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody UsersDTO usersDTO) {
        UUID usersId = usersService.insert(usersDTO);
        return new ResponseEntity<>(usersId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> getUsers(@PathVariable("id") UUID userId) {
        UsersDTO dto = usersService.findUserById(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
