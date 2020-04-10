package com.spring.boot.building.blocks.SpringBoot01.Repository;

import com.spring.boot.building.blocks.SpringBoot01.Entity.Order;
import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public Optional<Order> findOrderByOrderidAndUser(Long orderid, User user);
}
