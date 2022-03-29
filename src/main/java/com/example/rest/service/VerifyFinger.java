package com.example.rest.service;

import java.util.HashMap;
import java.util.Map;

public class VerifyFinger {
    private final boolean success;
    private final String message;

    public VerifyFinger(boolean success , String message)
    {
        this.success = success;
        this.message = message;
    }

    public Map<String, Object> getVerified()
    {
        HashMap<String , Object> map = new HashMap<>();
        map.put("success",success);
        map.put("message",message);
        return map;
    }
}
