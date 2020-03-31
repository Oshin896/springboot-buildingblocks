package com.spring.boot.building.blocks.SpringBoot01.Service;

import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
   private UserRepository userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public User createUser(User user){

        return userRepo.save(user);
    }
    public Optional<User> getUserById(long id){
        Optional<User> user=userRepo.findById(id);

        return user;
    }
    public User updateUserById(long id,User u){
        u.setId(id);
        return userRepo.save(u);
    }
    public Optional<User> deleteUserById(long id){
        Optional<User> u=userRepo.findById(id);
        if(!u.isPresent())
            return null;
        return u;
    }
    public User findByUserName(String username){
        return userRepo.findByUsername(username);
    }
}
