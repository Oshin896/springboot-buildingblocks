package com.spring.boot.building.blocks.SpringBoot01.Controller;

import com.spring.boot.building.blocks.SpringBoot01.DTO.UserRequestDTO;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserExistsException;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "User Manangement Rest Service", value = "UserController",description = "Controller for user mgt")
public class UserController extends RepresentationModel {
    @Autowired
    private UserService user;

    @GetMapping
    @ApiOperation(value ="retrieve list of users" )
    public List<User> getAllUsers(){
        return user.getAllUsers();
    }
    @PostMapping
    @ApiOperation(value ="create new users" )
    public ResponseEntity<Void> createUser(@ApiParam("User information for a new user to be created.")@Valid @RequestBody UserRequestDTO userRequestDTO, UriComponentsBuilder builder){
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
    public User getUserById(@PathVariable("id") @Min(1) long id) throws UserNotFoundException {

            Optional<User> userOptional= user.getUserById(id);
            if(!userOptional.isPresent())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return userOptional.get();
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
    public User deleteUserById(@PathVariable("id")long id)
    {
       Optional<User> optionalUser= user.deleteUserById(id);
       if(!optionalUser.isPresent())
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       return optionalUser.get();
    }

    @GetMapping("/username/{username}")
    public User getUserByUserName(@PathVariable("username") String username) throws UserNotFoundException {
        User user1=user.findByUserName(username);
        if(user1==null)
            throw new UserNotFoundException("User does not exists by username:"+username);
        return user1;
    }

}
