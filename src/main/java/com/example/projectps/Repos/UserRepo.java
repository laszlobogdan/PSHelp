package com.example.projectps.Repos;

import com.example.projectps.Model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserModel,Integer> {
//    UserModel findByUsername(String username);
}
