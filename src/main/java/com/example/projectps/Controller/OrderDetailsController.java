package com.example.projectps.Controller;

import com.example.projectps.Model.OrderDetailsModel;
import com.example.projectps.Model.UserModel;
import com.example.projectps.Repos.MenuRepo;
import com.example.projectps.Repos.OrderDetailsRepo;
import com.example.projectps.Repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/details")
public class OrderDetailsController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderDetailsRepo detailsRepo;
    @Autowired
    private MenuRepo menuRepo;

    @PostMapping("/add")
    private String add(OrderDetailsModel detailsModel) {
        OrderDetailsModel tempDetails = new OrderDetailsModel();
        tempDetails.setOrder(detailsModel.getOrder());
        tempDetails.setMeal(detailsModel.getMeal());
        tempDetails.setQuantity(detailsModel.getQuantity());
        if (orderRepo.findById(detailsModel.getOrder().getId()).get().getStatus() != 2) {
            if (menuRepo.findById(detailsModel.getMeal().getId()).get().getQuantity() >= detailsModel.getQuantity()) {
                detailsRepo.save(tempDetails);
                menuRepo.findById(detailsModel.getMeal().getId()).get()
                        .setQuantity(menuRepo.findById(detailsModel.getMeal().getId()).get().getQuantity() -
                                detailsModel.getQuantity());
                return "Meal added";
            }
        }
//        if (orderRepo.findById(detailsModel.getOrder()).get().getStatus() != 2) {
//            if (menuRepo.findById(detailsModel.getId()).get().getQuantity() >= detailsModel.getQuantity()) {
//                detailsRepo.save(tempDetails);
//                menuRepo.findById(detailsModel.getId()).get()
//                        .setQuantity(menuRepo.findById(detailsModel.getId()).get().getQuantity() -
//                                detailsModel.getQuantity());
//                return "Meal added";
//            }
//        }
        return "Permission denied";
    }

    @PostMapping("/delete/{id}")
    private String delete(@RequestParam Integer id) {
        if(orderRepo.findById(detailsRepo.findById(id).get().getOrder().getId()).get().getStatus()==0) {
            menuRepo.findById(detailsRepo.findById(id).get().getMeal().getId()).get().setQuantity(menuRepo.findById(detailsRepo.findById(id).get().getMeal().getId()).get().getQuantity() + detailsRepo.findById(id).get().getQuantity());
        }
//        if(orderRepo.findById(detailsRepo.findById(id).get().getIdorder()).get().getStatus()==0) {
//            menuRepo.findById(detailsRepo.findById(id).get().getId()).get().setQuantity(menuRepo.findById(detailsRepo.findById(id).get().getId()).get().getQuantity() + detailsRepo.findById(id).get().getQuantity());
//        }
        detailsRepo.deleteById(id);
        return ("Deleted order with id: " + id);
    }

}

