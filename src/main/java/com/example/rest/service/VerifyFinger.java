package com.example.rest.service;

public class VerifyFinger {
    private final String verify;

    public VerifyFinger(String verify)
    {
        this.verify = verify;
    }

    public String getFingerprint()
    {
        return verify;
    }
}
