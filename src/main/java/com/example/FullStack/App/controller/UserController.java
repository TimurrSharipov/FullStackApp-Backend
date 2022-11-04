package com.example.FullStack.App.controller;

import com.example.FullStack.App.exception.UserNotFoundException;
import com.example.FullStack.App.model.User;
import com.example.FullStack.App.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/user")
    public User newUser(@RequestBody User newUser){
        return userRepo.save(newUser);
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    
    @GetMapping("user/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) throws UserNotFoundException {

        return userRepo.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setName(newUser.getName());
                    return userRepo.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));


    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        if(!userRepo.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
    }
}
