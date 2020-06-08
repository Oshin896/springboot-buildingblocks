package com.spring.boot.building.blocks.SpringBoot01.Controller;

import com.spring.boot.building.blocks.SpringBoot01.DTO.UserMsDTO;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Mapper.UserMapper;
import com.spring.boot.building.blocks.SpringBoot01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserMsDTO> getAllUsersDTO(){
        return userMapper.usersToUsersDTO(userRepository.findAll());
    }

    @GetMapping("{id}")
    public UserMsDTO getUserById(@PathVariable Long id){
        Optional<User> optionalUser=userRepository.findById(id);
        if(!optionalUser.isPresent()) {
           return null;
        }
        User user = optionalUser.get();
        return userMapper.userToUserDTO(user);
    }

}
