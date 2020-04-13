package com.spring.boot.building.blocks.SpringBoot01.DTO;

import com.spring.boot.building.blocks.SpringBoot01.Entity.User;

public class OrderRequestDTO {


    private String orderdescription;
    private User user;

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
