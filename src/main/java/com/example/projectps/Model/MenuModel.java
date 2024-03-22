package com.example.projectps.Model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="Menu")
@NoArgsConstructor
@AllArgsConstructor
public class MenuModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idmenu")
    private Integer id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "price")
    private  Integer price;

    @Setter
    @Column(name = "quantity")
    private Integer quantity;
}
