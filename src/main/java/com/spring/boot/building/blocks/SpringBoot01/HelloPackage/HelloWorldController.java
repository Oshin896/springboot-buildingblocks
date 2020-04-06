package com.spring.boot.building.blocks.SpringBoot01.HelloPackage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
@GetMapping("/hello")
    public String helloWorld(){
        return "Hello-World1 ";
    }
@GetMapping("/helloBean")
    public UserDetail helloWorldBean(){
    return new UserDetail("Oshin","Banerjee","Jamshedpur");
    }
}
