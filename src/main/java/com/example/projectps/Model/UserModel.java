package com.example.projectps.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table(name="User")
@NoArgsConstructor
@AllArgsConstructor

public class UserModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Setter
    @Column(name="name")
    private String name;

    @Setter
    @Column(name="username")
    private String username;

    @Setter
    @Column(name="password")
    private String password;

    @Setter
    @Column(name="type")
    private Boolean type;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    @Setter
    private List<OrderModel> orders = new ArrayList<>();

}
