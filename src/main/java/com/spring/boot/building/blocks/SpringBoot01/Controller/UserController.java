package com.spring.boot.building.blocks.SpringBoot01.Controller;

import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserExistsException;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Void> createUser(@RequestBody User user1, UriComponentsBuilder builder){
        try {
             user.createUser(user1);
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(user1.getId()).toUri());
            return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
        } catch (UserExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
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
    public User updateUserById(@RequestBody User u, @PathVariable("id")long id)
    {
        try {
            return user.updateUserById(id,u);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/users/{id}")
    public Optional<User> deleteUserById(@PathVariable("id")long id){
       return user.deleteUserById(id);
    }

    @GetMapping("/users/username/{username}")
    public User getUserByUserName(@PathVariable("username") String username){
        return user.findByUserName(username);
    }
}
