package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.controllers;

import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.dtos.ConsumptionDTO;
import com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.services.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/consumption")
public class ConsumptionController {
    private final ConsumptionService consumptionService;

    @Autowired
    public ConsumptionController(ConsumptionService consumptionService){
        this.consumptionService=consumptionService;
    }


    @GetMapping()
    public ResponseEntity<List<ConsumptionDTO>> getConsumptions(){
        List<ConsumptionDTO> dtos = consumptionService.findConsumptions();
        for (ConsumptionDTO dto : dtos) {
            Link consumptionLink = linkTo(methodOn(ConsumptionController.class)
                    .getConsumptions(dto.getId())).withRel("consumptionDetails");
            dto.add(consumptionLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ConsumptionDTO> getConsumptions(@PathVariable("id") UUID consumptionId) {
        ConsumptionDTO dto = consumptionService.findConsumptionById(consumptionId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertConsumption(@Valid @RequestBody ConsumptionDTO consumptionDTO) {
        UUID consumptionId = consumptionService.insert(consumptionDTO);
        return new ResponseEntity<>(consumptionId, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteConsumption(@PathVariable("id") UUID consumptionId) {
        consumptionService.delete(consumptionId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UUID> updateConsumption(@PathVariable("id") UUID consumptionId, @Valid @RequestBody ConsumptionDTO consumptionDTO) {
        consumptionId = consumptionService.updateConsumption(consumptionId,consumptionDTO);
        return new ResponseEntity<>(consumptionId, HttpStatus.OK);
    }


}
