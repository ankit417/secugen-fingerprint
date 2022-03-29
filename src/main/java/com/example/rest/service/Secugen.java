package com.example.rest.service;

import java.util.HashMap;
import java.util.Map;

public class Secugen {
    private final boolean success;
    private final String message;
    private final String file;
    public Secugen(boolean success , String message , String file)
    {
        this.success=success;
        this.message = message;
        this.file = file;
    }

    public Map<String,Object> getFingerprint()
    {
        HashMap<String,Object> map = new HashMap<>();
        map.put("success",success);
        map.put("message",message);
        map.put("file",file);
        return map;
    }
}
