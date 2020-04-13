package com.spring.boot.building.blocks.SpringBoot01.Controller;

import com.spring.boot.building.blocks.SpringBoot01.DTO.UserRequestDTO;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserExistsException;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;


@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController extends RepresentationModel {
    @Autowired
    private UserService user;

    @GetMapping
    public List<User> getAllUsers(){
        return user.getAllUsers();
    }
    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO, UriComponentsBuilder builder){
        try {
            User u= user.createUser(userRequestDTO);
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(u.getId()).toUri());
            return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
        } catch (UserExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) long id){
        try {
            return user.getUserById(id);
        }catch (UserNotFoundException uex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,uex.getMessage());
        }
    }
    @PutMapping("/{id}")
    public User updateUserById(@RequestBody UserRequestDTO userRequestDTO, @PathVariable("id")long id)
    {
        try {
            return user.updateUserById(id,userRequestDTO);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public Optional<User> deleteUserById(@PathVariable("id")long id){
       return user.deleteUserById(id);
    }

    @GetMapping("/username/{username}")
    public User getUserByUserName(@PathVariable("username") String username) throws UserNotFoundException {
        User user1=user.findByUserName(username);
        if(user1==null)
            throw new UserNotFoundException("User does not exists by username:"+username);
        return user1;
    }

}
