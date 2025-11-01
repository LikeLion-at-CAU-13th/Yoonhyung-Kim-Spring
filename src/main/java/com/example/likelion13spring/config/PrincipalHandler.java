package com.example.likelion13spring.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalHandler {
    public static String getUsernameFromPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.toString();
    }

    public static Long getMemberIdFromPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Long)principal;
    }
}
