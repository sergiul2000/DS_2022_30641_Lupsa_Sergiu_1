package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities.Consumption;
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
    private String name;
    private int serial_nr;
    private float consumtion;
    private List<Consumption> consumptionList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceDTO)) return false;
        if (!super.equals(o)) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return getSerial_nr() == deviceDTO.getSerial_nr() && Float.compare(deviceDTO.getConsumtion(), getConsumtion()) == 0 && Objects.equals(getId(), deviceDTO.getId()) && Objects.equals(getName(), deviceDTO.getName()) && Objects.equals(getConsumptionList(), deviceDTO.getConsumptionList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName(), getSerial_nr(), getConsumtion(), getConsumptionList());
    }
}
