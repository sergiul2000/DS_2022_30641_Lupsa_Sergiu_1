package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos;

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
public class DeviceDTO extends RepresentationModel<DeviceDTO> {
    private UUID id;
    private String address;
    private String description;
    private int maxim_hourly_energy;
    //private List<Consumption> consumptionList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceDTO)) return false;
        if (!super.equals(o)) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return getMaxim_hourly_energy() == deviceDTO.getMaxim_hourly_energy() && Objects.equals(getId(), deviceDTO.getId()) && Objects.equals(getAddress(), deviceDTO.getAddress()) && Objects.equals(getDescription(), deviceDTO.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getAddress(), getDescription(), getMaxim_hourly_energy());
    }
}
