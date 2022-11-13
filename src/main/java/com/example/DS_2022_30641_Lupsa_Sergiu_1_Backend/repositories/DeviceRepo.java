package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.repositories;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeviceRepo extends JpaRepository<Device, UUID> {
//    List<Device> findById(UUID id);
}
