package com.spring.boot.building.blocks.SpringBoot01.Exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }

}
