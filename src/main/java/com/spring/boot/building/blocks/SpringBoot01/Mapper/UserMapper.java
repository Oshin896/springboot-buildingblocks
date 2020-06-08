package com.spring.boot.building.blocks.SpringBoot01.Mapper;

import com.spring.boot.building.blocks.SpringBoot01.DTO.UserMsDTO;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    //user to userDTO
    @Mappings({
    @Mapping(source = "email",target = "emailAddress"),
            @Mapping(source = "role",target = "roleName")
    })
    UserMsDTO userToUserDTO(User user);

    //List<User> to List<UserDTO>
    List<UserMsDTO> usersToUsersDTO(List<User> users);


}
