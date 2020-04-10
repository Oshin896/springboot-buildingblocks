package com.spring.boot.building.blocks.SpringBoot01.Exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
