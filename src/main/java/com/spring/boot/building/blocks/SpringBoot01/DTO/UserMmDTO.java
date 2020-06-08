package com.spring.boot.building.blocks.SpringBoot01.DTO;

import com.spring.boot.building.blocks.SpringBoot01.Entity.Order;

import java.util.List;

public class UserMmDTO {

    private long id;
    private String username;
    private List<Order> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
