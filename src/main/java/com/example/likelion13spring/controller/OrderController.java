package com.example.likelion13spring.controller;

import com.example.likelion13spring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // 추가
import com.example.likelion13spring.dto.request.OrderRequestDto;
import com.example.likelion13spring.dto.request.OrderDeleteRequestDto; // 추가
import com.example.likelion13spring.dto.request.OrderUpdateRequestDto; // 추가
import com.example.likelion13spring.dto.response.OrderResponseDto;





@RestController
@RequiredArgsConstructor
@RequestMapping("/orders") // 공통 경로
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }


    // 구매자별 주문 조회
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<OrderResponseDto>> getOrderByBuyer(@PathVariable Long buyerId) {
        return ResponseEntity.ok(orderService.getOrderByBuyer(buyerId));
    }

    // 단건 주문 조회
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // 주문 수정 -- preparation의 경우만 가능
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id,
                                                          @RequestBody OrderUpdateRequestDto dto) {
        return ResponseEntity.ok(orderService.updateOrder(id, dto));
    }

    // 주문 삭제 -- completed의 경우만 가능
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id,
                                                @RequestBody OrderDeleteRequestDto dto) {
        orderService.deleteOrder(id, dto);
        return ResponseEntity.ok("주문이 성공적으로 삭제되었습니다.");
    }
}
