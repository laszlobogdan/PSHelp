package com.example.projectps.Repos;

import com.example.projectps.Model.OrderModel;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<OrderModel,Integer> {
}
