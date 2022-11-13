package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO extends RepresentationModel<UsersDTO> {
    private UUID id;
    private String name;
    private String address;
    private int age;
    private int role;
    private List<Device> deviceList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDTO)) return false;
        if (!super.equals(o)) return false;
        UsersDTO usersDTO = (UsersDTO) o;
        return getAge() == usersDTO.getAge() && getRole() == usersDTO.getRole() && Objects.equals(getId(), usersDTO.getId()) && Objects.equals(getName(), usersDTO.getName()) && Objects.equals(getAddress(), usersDTO.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName(), getAddress(), getAge(), getRole());
    }
}
