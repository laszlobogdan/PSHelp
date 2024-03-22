package com.example.projectps.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="OrderDetails")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="iddetail")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "idorder")
    private OrderModel order;

//    @Column(name = "idorder")
//    @Setter
//    private Integer idorder;

    @ManyToOne
    @JoinColumn(name = "idmenu")
    @JsonIgnore
    private MenuModel meal;
//
//    @Column(name = "idmenu")
//    @Setter
//    private Integer idmenu;

    @Setter
    @Column(name = "quantity")
    private Integer quantity;
    // Custom setter method for OrderModel
    public void setOrder(OrderModel order) {
        this.order = order;
    }

    // Custom setter method for MenuModel
    public void setMeal(MenuModel meal) {
        this.meal = meal;
    }
}
