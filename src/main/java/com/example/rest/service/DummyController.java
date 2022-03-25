package com.example.rest.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @GetMapping("/dummytest")
    public Dummy helloDummy()
    {
        return new Dummy(true,"fingerprint data","template data");
    }

    
}
