package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.UsersDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders.UsersBuilder;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Users;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.repositories.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);
    private final UsersRepo usersRepo;

    @Autowired
    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public List<UsersDTO> findUsers(){
        List<Users> usersList = usersRepo.findAll();
        return usersList.stream()
                .map(UsersBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    public UsersDTO findUserById(UUID id) {
        Optional<Users> prosumerOptional = usersRepo.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + id);
        }
        return UsersBuilder.toUserDTO(prosumerOptional.get());
    }

    public UUID insert(UsersDTO usersDTO) {
        Users users = UsersBuilder.toEntity(usersDTO);
        users = usersRepo.save(users);
        LOGGER.debug("Person with id {} was inserted in db", users.getId());
        return users.getId();
    }

}
