package com.spring.boot.building.blocks.SpringBoot01.Exception;

public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}
