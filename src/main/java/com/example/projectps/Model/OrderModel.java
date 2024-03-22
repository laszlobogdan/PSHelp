package com.example.projectps.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Getter
@Entity
@Table(name="Orders")
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idorder")
    private Integer id;

    @Setter
    @Column(name = "cost")
    private Integer cost;

    @Setter
    @Column(name = "status")
    private Integer status;

    @Setter
    @Column(name = "date")
    private Date date;


    @ManyToOne
    @JoinColumn(name = "userid")
    @Setter
    private UserModel user;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    @Setter
    private List<OrderDetailsModel> orderDetails = new ArrayList<>();
}
