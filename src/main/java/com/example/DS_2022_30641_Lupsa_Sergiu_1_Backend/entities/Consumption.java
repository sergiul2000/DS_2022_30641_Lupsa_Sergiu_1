package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Consumption {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name="value", nullable = false)
    private float value;

    @Column(name="timestamp", nullable = false)
    private LocalDateTime timestamp;

    public Consumption(UUID id, float value) {
        this.id = id;
        this.value = value;
        LocalDateTime now = LocalDateTime.now();

//        System.out.println(dtf.format(now));
        this.timestamp=now;
    }
//    public static void main(String args[]){
//        Consumption consumption = new Consumption( UUID.fromString("91f5bab7-7024-4b6d-8650-7cbe0d0b8da3"),21.6f);
//    }

}