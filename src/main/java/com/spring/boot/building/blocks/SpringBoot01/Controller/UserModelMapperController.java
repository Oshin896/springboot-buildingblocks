package com.spring.boot.building.blocks.SpringBoot01.Controller;


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
@RequestMapping(value = "/modelmapper/users")
public class UserModelMapperController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserMmDTO getUserById(@PathVariable("id") @Min(1) long id){
        try {
            Optional<User> optionalUser=userService.getUserById(id);
            if(!optionalUser.isPresent()){
                throw new UserNotFoundException("User not found");
            }
            User user=optionalUser.get();
            UserMmDTO userMmDTO=modelMapper.map(user,UserMmDTO.class);
            return userMmDTO;
        }catch (UserNotFoundException uex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,uex.getMessage());
        }
    }
}
