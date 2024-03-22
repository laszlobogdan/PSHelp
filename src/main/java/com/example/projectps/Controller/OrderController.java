package com.example.projectps.Controller;

import com.example.projectps.Model.OrderModel;
import com.example.projectps.Model.UserModel;
import com.example.projectps.Repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;
    UserModel currUser=UserController.currUser;

    @PostMapping("/create")
    private String create(@RequestBody OrderModel order) {
        OrderModel tempOrder = new OrderModel();
        tempOrder.setCost(order.getCost());
        tempOrder.setDate(order.getDate());
        tempOrder.setStatus(order.getStatus());
        orderRepo.save(tempOrder);
        return "Order created";

    }

    @PostMapping("/update/{id}")
    private String update(@RequestParam Integer id, @RequestBody OrderModel order) {
        Optional<OrderModel> tOrder = Optional.ofNullable(orderRepo.findById(id).orElse(null));
        OrderModel tempOrder = new OrderModel();
        if (tOrder.isPresent()) {
            tempOrder.setCost(order.getCost());
            tempOrder.setDate(order.getDate());
            tempOrder.setStatus(order.getStatus());
            orderRepo.save(tempOrder);
        }
        return "Order updated";
    }

    @PostMapping("/delete/{id}")
    private String delete(@RequestParam Integer id) {
        orderRepo.deleteById(id);
        return ("Deleted order with id: " + id);
    }

    @GetMapping("/allOrderds")
    private Iterable<OrderModel> viewAll(){
        return orderRepo.findAll();
    }

    @GetMapping("/findById/{id}")
    private Optional<OrderModel> byId(@PathVariable Integer id){
        return orderRepo.findById(id);
    }

}
