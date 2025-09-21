package com.example.likelion13spring.dto.request;

import com.example.likelion13spring.enums.DeliverStatus;
import lombok.Getter;

@Getter
public class OrderUpdateRequestDto {
    private DeliverStatus deliverStatus;
    private Long buyerId;
}
