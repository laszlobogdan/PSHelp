package com.example.projectps.Controller;

import com.example.projectps.Model.MenuModel;
import com.example.projectps.Model.UserModel;
import com.example.projectps.Repos.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuRepo menuRepo;
    UserModel currUser=UserController.currUser;


    @PostMapping("/add")
    private String add(@RequestBody MenuModel menu){
        MenuModel tMenu= new MenuModel();
        tMenu.setName(menu.getName());
        tMenu.setPrice(menu.getPrice());
        tMenu.setQuantity(menu.getQuantity());
        if(currUser.getType()) {
            menuRepo.save(tMenu);
            return "eal created";
        }
        return "Permission denied";
    }

    @PostMapping("/update/{id}")
    private String update(@RequestParam Integer id, @RequestBody MenuModel menu){
        Optional<MenuModel> tMenu= Optional.ofNullable(menuRepo.findById(id).orElse(null));
        MenuModel tempMenu = new MenuModel();
        if (tMenu.isPresent()) {
            tempMenu.setName(menu.getName());
            tempMenu.setPrice(menu.getPrice());
            tempMenu.setQuantity(menu.getQuantity());
        }
        if (currUser.getType()) {
            menuRepo.save(tempMenu);
            return "Meal updated";
        }
        return "Permission denied";
    }

    @PostMapping("/delete/{id}")
    private String delete(@RequestParam Integer id){
        if (currUser.getType()){
            menuRepo.deleteById(id);
            return ("Deleted user with id: "+id);
        }
        return "Permission denied";
    }
    @GetMapping("/allMeals")
    private Iterable<MenuModel> viewAll(){
        return menuRepo.findAll();
    }

    @GetMapping("/findById/{id}")
    private Optional<MenuModel> byId(@PathVariable Integer id){
        return menuRepo.findById(id);
    }



}
