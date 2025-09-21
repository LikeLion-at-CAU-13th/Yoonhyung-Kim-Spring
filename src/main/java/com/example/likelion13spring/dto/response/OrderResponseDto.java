package com.example.likelion13spring.dto.response;

import com.example.likelion13spring.domain.Orders;
import com.example.likelion13spring.enums.DeliverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private DeliverStatus deliverStatus;

    // 추가
    public static OrderResponseDto fromEntity(Orders order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .deliverStatus(order.getDeliverStatus())
                .build();
    }
}