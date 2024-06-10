package com.example.rental.security;

import org.springframework.security.core.AuthenticationException;

public class CustomerAuthenticationException extends AuthenticationException {

    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}
