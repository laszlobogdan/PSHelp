package com.example.projectps.Repos;

import com.example.projectps.Controller.OrderDetailsController;
import com.example.projectps.Model.OrderDetailsModel;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepo extends CrudRepository<OrderDetailsModel,Integer> {
}
