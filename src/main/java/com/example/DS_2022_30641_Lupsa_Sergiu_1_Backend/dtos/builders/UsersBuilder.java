package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.builders;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.UsersDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Users;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsersBuilder {
    public static UsersDTO toUserDTO(Users users){
        return new UsersDTO( users.getId(),users.getName(), users.getAddress(), users.getAge(), users.getRole());
    }

    public static Users toEntity(UsersDTO usersDTO) {
        return new Users(usersDTO.getId(),
                usersDTO.getName(),
                usersDTO.getAddress(),
                usersDTO.getAge(),
                usersDTO.getRole());
    }
}
