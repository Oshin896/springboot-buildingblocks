package com.spring.boot.building.blocks.SpringBoot01.Controller;


import com.spring.boot.building.blocks.SpringBoot01.DTO.UserDTOv1;
import com.spring.boot.building.blocks.SpringBoot01.DTO.UserDTOv2;
import com.spring.boot.building.blocks.SpringBoot01.DTO.UserMmDTO;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping(value = "/versioning/uri/users/")
public class UserUriVersioningController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping({ "/v1.0/{id}", "/v1.1/{id}" })
    public UserDTOv1 getUserByIdv1(@PathVariable("id") @Min(1) long id){
        try {
            Optional<User> optionalUser=userService.getUserById(id);
            if(!optionalUser.isPresent()){
                throw new UserNotFoundException("User not found");
            }
            User user=optionalUser.get();
            UserDTOv1 userDTOv1=modelMapper.map(user, UserDTOv1.class);
            return userDTOv1;
        }catch (UserNotFoundException uex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,uex.getMessage());
        }
    }

    @GetMapping("/v2.0/{id}")
    public UserDTOv2 getUserByIdv2(@PathVariable("id") @Min(1) long id){
        try {
            Optional<User> optionalUser=userService.getUserById(id);
            if(!optionalUser.isPresent()){
                throw new UserNotFoundException("User not found");
            }
            User user=optionalUser.get();
            UserDTOv2 userDTOv2=modelMapper.map(user, UserDTOv2.class);
            return userDTOv2;
        }catch (UserNotFoundException uex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,uex.getMessage());
        }
    }
}
