package com.otp.OtpEmailMobile.Controller;


import com.otp.OtpEmailMobile.Entity.User;
import com.otp.OtpEmailMobile.Exception.UserNotFoundException;
import com.otp.OtpEmailMobile.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController("/")
public class UserController {
    @Autowired
    private UserService user;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return user.getAllUsers();
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user1){
        return user.createUser(user1);
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") long id){
        try {
            return user.getUserById(id);
        }catch (UserNotFoundException uex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,uex.getMessage());
        }
    }
    @PutMapping("/users/{id}")
    public User updateUserById(@RequestBody User u,@PathVariable("id")long id){
        return user.updateUserById(id,u);
    }
//    @DeleteMapping("/users/{id}")
//    public Optional<User> deleteUserById(@PathVariable("id")long id){
//        return user.deleteUserById(id);
//    }

    @GetMapping("/users/username/{username}")
    public User getUserByUserName(@PathVariable("username") String username){
        return user.findByUserName(username);
    }
}
