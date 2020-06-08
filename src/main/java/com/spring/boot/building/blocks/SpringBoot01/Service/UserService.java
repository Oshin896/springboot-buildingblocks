package com.spring.boot.building.blocks.SpringBoot01.Service;


import com.spring.boot.building.blocks.SpringBoot01.DTO.UserRequestDTO;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserExistsException;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;

import com.spring.boot.building.blocks.SpringBoot01.Mapper.UserMappers;
import com.spring.boot.building.blocks.SpringBoot01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
   private UserRepository userRepo;
    @Autowired
    private UserMappers userMapper;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public User createUser(UserRequestDTO userRequestDTO) throws UserExistsException {
        User u=userRepo.findByUsername(userRequestDTO.getUsername());
        if(u!=null)
            throw new UserExistsException("User already exists by useraname:"+userRequestDTO.getUsername());
        User user=new User();

        return userRepo.save(userMapper.convertRequestDTOtoModel(userRequestDTO,user));
    }
    public Optional<User> getUserById(long id) throws UserNotFoundException {
        Optional<User> user=userRepo.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("User not found for the id:"+id);
        }
        return user;
    }
    public User updateUserById(long id,UserRequestDTO userRequestDTO) throws UserNotFoundException {
        Optional<User> user=userRepo.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found for the id:"+id);
        }
        User u=new User();
        u.setId(id);

        return userRepo.save(userMapper.convertRequestDTOtoModel(userRequestDTO,u));
    }
    public Optional<User> deleteUserById(long id) {
        Optional<User> u=userRepo.findById(id);
       if(!u.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found for the id:"+id);
        return u;
    }
    public User findByUserName(String username){
        return userRepo.findByUsername(username);
    }
}
