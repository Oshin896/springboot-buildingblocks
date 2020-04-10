package com.spring.boot.building.blocks.SpringBoot01.Controller;


import com.spring.boot.building.blocks.SpringBoot01.Entity.Order;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import com.spring.boot.building.blocks.SpringBoot01.Exception.OrderNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Exception.UserNotFoundException;
import com.spring.boot.building.blocks.SpringBoot01.Repository.OrderRepository;
import com.spring.boot.building.blocks.SpringBoot01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
        Optional<User> userOptional=userRepository.findById(userid);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User Not Found");
        }
        return userOptional.get().getOrders();
    }

    @PostMapping("/{userid}/orders")
    public void createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional=userRepository.findById(userid);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User Not Found");

        }
        User user= userOptional.get();
        order.setUser(user);
        orderRepository.save(order);
    }

    @GetMapping("/{userid}/orders/{orderid}")
    public Order getOrderByOrderId(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid) throws UserNotFoundException, OrderNotFoundException {
        Optional<User> userOptional=userRepository.findById(userid);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User Not Found");
        }
        Optional<Order> orderOptional=orderRepository.findById(orderid);
        if(!orderOptional.isPresent()){
            throw new OrderNotFoundException("Order not found");
        }

        Optional<Order> orderPresent=orderRepository.findOrderByOrderidAndUser(orderid,userOptional.get());
        if(!orderPresent.isPresent())
            throw  new OrderNotFoundException("Order with orderid:"+orderid+" is not found with user:"+userid);

        return orderPresent.get();
    }

}
