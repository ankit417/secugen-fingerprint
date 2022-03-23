package com.example.rest.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.*;

@RestController
public class RestRequestController {

    @RequestMapping("/hello")
    public String hello()
    {
        return "Hello world";
    }
    
    @GetMapping(value="/callthehello")
    private String callTheHello()
    {
        String uri = "localhost:8081";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @GetMapping(value="/postfinger")
    public String postFingerprints()
    {
        String uri = "http://192.168.1.77:8848/api/common-doc";
        File idFile = new File ("C:\\Users\\achar\\Downloads\\rest_service\\rest-service\\secondImage.png");
        File jsonFile = new File ("C:\\Users\\achar\\Downloads\\rest_service\\rest-service\\sample2.iso19794");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("imageFile", new FileSystemResource(idFile));
        body.add("bioFile", new FileSystemResource(jsonFile));
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + "12345");
        // headers.set("Authorization", "Bearer " + token);
        
        HttpEntity<MultiValueMap<String, Object>> requestEntity= new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.exchange(
                            uri, HttpMethod.POST,requestEntity,
                            String.class).getBody().toString();
            
            System.out.println(result);
            return result;
            
            // return "hello";
        }
}
