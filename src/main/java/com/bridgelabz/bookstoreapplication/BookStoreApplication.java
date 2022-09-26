package com.bridgelabz.bookstoreapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BookStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
        log.info("Application Loaded Successfully.");
        System.out.println("\n < ------ Welcome to Book Store Application ------ > \n");
    }
}