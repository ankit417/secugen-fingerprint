package com.example.rest.service;

import java.util.HashMap;
import java.util.Map;

public class Dummy {
    
    private final Boolean success;
    private final String fingerprint;
    private final String template;
    public Dummy( Boolean success , String fingerprint , String template)
    {
        this.success = success;
        this.fingerprint = fingerprint;
        this.template = template;
        
    }
    public Map<String,Object>  getData()
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",success);
        map.put("fingerprint",fingerprint);
        map.put("template",template);
        return map;
    }   
}
