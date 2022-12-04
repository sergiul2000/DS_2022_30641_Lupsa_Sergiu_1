package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionDTO extends RepresentationModel<ConsumptionDTO> {

    private UUID id;
    private float value;
    private LocalDateTime timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumptionDTO)) return false;
        if (!super.equals(o)) return false;
        ConsumptionDTO that = (ConsumptionDTO) o;
        return Float.compare(that.getValue(), getValue()) == 0 && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getValue());
    }
}
