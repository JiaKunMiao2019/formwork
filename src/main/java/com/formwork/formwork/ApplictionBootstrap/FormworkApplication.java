package com.formwork.formwork.ApplictionBootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.formwork.formwork")
public class FormworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormworkApplication.class, args);
    }

}
