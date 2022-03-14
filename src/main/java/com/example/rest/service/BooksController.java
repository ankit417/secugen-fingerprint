package com.example.rest.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    
    @GetMapping("/books")
    public Books books(){
        String test = "The alchemist";
        return new Books(test);
    }
}
