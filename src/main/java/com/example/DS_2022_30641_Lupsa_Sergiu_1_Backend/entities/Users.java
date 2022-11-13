package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false,unique = true)//ca sa fie unica, mai pui in paranteze unique = true
    private String name;

    @Column(name = "address", nullable = false)//ca sa fie unica, mai pui in paranteze unique = true
    private String address;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "role", nullable = false)
    private int role;



//    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
//    private List<Device> devices;
}
