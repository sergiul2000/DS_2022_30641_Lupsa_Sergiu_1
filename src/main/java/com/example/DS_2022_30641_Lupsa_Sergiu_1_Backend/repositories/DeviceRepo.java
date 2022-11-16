package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.repositories;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepo extends JpaRepository<Device, UUID> {
//    List<Device> findById(UUID id);
}
