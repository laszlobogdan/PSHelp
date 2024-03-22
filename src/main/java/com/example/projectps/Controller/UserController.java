package com.example.projectps.Controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.projectps.Model.UserModel;
import com.example.projectps.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    protected static UserModel currUser;

    @Autowired
    private UserRepo userRepo;
    @PostMapping("/create")
    private String create(@RequestBody UserModel user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        UserModel tempUser=new UserModel();
        tempUser.setName(user.getName());
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(hashedPassword);
        tempUser.setType(user.getType());
        if(currUser.getType()) {
            userRepo.save(tempUser);
            return "User created";
        }
        return "Permission denied";
    }

    @PostMapping("/update/{id}")
    private String update(@RequestParam Integer id,@RequestBody UserModel user){
        Optional<UserModel> tUser= Optional.ofNullable(userRepo.findById(id).orElse(null));
        UserModel tempUser = new UserModel();
        if (tUser.isPresent()) {
            tempUser.setName(user.getName());
            tempUser.setUsername(user.getUsername());
            tempUser.setPassword(user.getPassword());
            tempUser.setType(user.getType());
        }
        if (currUser.getType()) {
            userRepo.save(tempUser);
            return "User updated";
        }
        return "Permission denied";
    }

    @PostMapping("/delete/{id}")
    private String delete(@RequestParam Integer id){
        if (currUser.getType()){
            userRepo.deleteById(id);
            return ("Deleted user with id: "+id);
        }
        return "Permission denied";
    }

    @GetMapping("/allUsers")
    private Iterable<UserModel> viewAll(){
        return userRepo.findAll();
    }

    @GetMapping("/findById/{id}")
    private Optional<UserModel> byId(@PathVariable Integer id){
        return userRepo.findById(id);
    }

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
//       Iterable<UserModel> optionalUser = userRepo.findAll();
        Iterable<UserModel> optionalUser = userRepo.findAll();
        Iterator<UserModel> it = optionalUser.iterator();
       while(it.hasNext()){
           UserModel temp = it.next();
            if (passwordEncoder.matches(password, temp.getPassword())) {
                currUser=temp;
                return "Login successful";
            } else {
                return "Incorrect password";
            }
       }
       return "User not found";
    }
}
