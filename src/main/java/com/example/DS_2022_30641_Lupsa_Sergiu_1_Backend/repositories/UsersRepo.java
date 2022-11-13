package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.repositories;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsersRepo extends JpaRepository<Users, UUID> {
    List<Users> findByName(String name);
}
