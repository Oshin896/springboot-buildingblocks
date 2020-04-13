package com.spring.boot.building.blocks.SpringBoot01.Controller;

import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/hateoas/users")
public class UserHateoasController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable("id") @Min(1) long id){
        try {
            Optional<User> optionalUser=userService.getUserById(id);
            User user=optionalUser.get();
            Long userid=user.getId();
            Link selfLink= WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
            user.add(selfLink);
            EntityModel<User> finalResource=new EntityModel<User>(user);
            return finalResource;
        }catch (UserNotFoundException uex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,uex.getMessage());
        }
    }
    @GetMapping
    public CollectionModel<User> getAllUsers(){
        List<User> users=userService.getAllUsers();

        users.forEach(
               user -> {
                   user.add(WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getId()).withSelfRel());
                   user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(user.getId())).withRel("all-orders"));
               });

        CollectionModel<User> finalResources=new CollectionModel<User>(users,WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel());
        return finalResources;
    }
}
