package com.example.likelion13spring.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("/health") //url에 추가할 수 있음 - 현재는 없어도 상관없음
public class HealthCheckController {

    @GetMapping("/state")
    public String healthCheck(){
        Random rand = new Random();
        int randomInt = rand.nextInt(3);
        return switch (randomInt) {
            case 0 -> "Dead";
            case 1 -> "Fine";
            case 2 -> "Healthy";
            default -> "Error";
        };
    }
}
