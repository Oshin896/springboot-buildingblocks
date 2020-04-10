package com.spring.boot.building.blocks.SpringBoot01.Controller;


import com.spring.boot.building.blocks.SpringBoot01.Entity.Order;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Exception.OrderNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Repository.OrderRepository;
import com.spring.boot.building.blocks.SpringBoot01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid)  {
        Optional<User> userOptional=userRepository.findById(userid);
        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        return userOptional.get().getOrders();
    }

    @PostMapping("/{userid}/orders")
    public void createOrder(@PathVariable Long userid, @RequestBody Order order) {
        Optional<User> userOptional=userRepository.findById(userid);
        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");

        }
        User user= userOptional.get();
        order.setUser(user);
        orderRepository.save(order);
    }

    @GetMapping("/{userid}/orders/{orderid}")
    public Order getOrderByOrderId(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid){
        Optional<User> userOptional=userRepository.findById(userid);
        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        Optional<Order> orderOptional=orderRepository.findById(orderid);
        if(!orderOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found");
        }

        Optional<Order> orderPresent=orderRepository.findOrderByOrderidAndUser(orderid,userOptional.get());
        if(!orderPresent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order with orderid:"+orderid+" is not found with user:"+userid);

        return orderPresent.get();
    }

}
