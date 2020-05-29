package com.spring.boot.building.blocks.SpringBoot01.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Entity.View;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/jsonView/users")
public class UserJsonViewController {
    @Autowired
    private UserService userService;

    //getByUserId--External
    @JsonView(View.External.class)
    @GetMapping("/external/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) long id){
        try {
            return userService.getUserById(id);
        }catch (UserNotFoundException uex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,uex.getMessage());
        }
    }

    //getByUserId--Internal
    @JsonView(View.Internal.class)
    @GetMapping("/internal/{id}")
    public Optional<User> getUserById2(@PathVariable("id") @Min(1) long id){
        try {
            return userService.getUserById(id);
        }catch (UserNotFoundException uex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,uex.getMessage());
        }
    }

}
