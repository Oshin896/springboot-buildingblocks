package com.spring.boot.building.blocks.SpringBoot01.Mapper;

import com.spring.boot.building.blocks.SpringBoot01.DTO.UserRequestDTO;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMappers {
    public User convertRequestDTOtoModel(UserRequestDTO userRequestDTO, User user){
        user.setEmail(userRequestDTO.getEmail());
        user.setUsername(userRequestDTO.getUsername());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setOrders(userRequestDTO.getOrders());
        user.setRole(userRequestDTO.getRole());
        user.setSsn(userRequestDTO.getSsn());
        return user;

    }
}
