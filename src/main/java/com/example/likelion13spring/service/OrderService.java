package com.example.likelion13spring.service;

import com.example.likelion13spring.enums.DeliverStatus;
import com.example.likelion13spring.domain.Member;
import com.example.likelion13spring.domain.Orders;
import com.example.likelion13spring.dto.request.OrderDeleteRequestDto;
import com.example.likelion13spring.dto.request.OrderRequestDto;
import com.example.likelion13spring.dto.request.OrderUpdateRequestDto;
import com.example.likelion13spring.dto.response.OrderResponseDto;
import com.example.likelion13spring.repository.MemberRepository;
import com.example.likelion13spring.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Member member = memberRepository.findById(dto.getBuyerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 구매자입니다."));

        if (!member.isBuyer()) {
            throw new IllegalArgumentException("주문은 구매자만 등록할 수 있습니다.");
        }

        Orders saved = orderRepository.save(dto.toEntity(member));
        return OrderResponseDto.fromEntity(saved);
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderResponseDto::fromEntity)
                .toList();
    }

    // 구매자별 조회
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrderByBuyer(Long buyerId) {
        memberRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 구매자입니다."));

        return orderRepository.findAllByBuyerId(buyerId).stream()
                .map(OrderResponseDto::fromEntity)
                .toList();
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));
        return OrderResponseDto.fromEntity(order);
    }

    @Transactional
    public OrderResponseDto updateOrder(Long orderId, OrderUpdateRequestDto dto) {
        Member member = memberRepository.findById(dto.getBuyerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 구매자입니다."));

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        if (!order.getBuyer().getId().equals(member.getId())) {
            throw new IllegalArgumentException("구매자 본인이 주문을 수정할 수 있습니다.");
        }

        if (order.getDeliverStatus() != DeliverStatus.PREPARATION) {
            throw new IllegalStateException("주문은 PREPARATION 상태일 때만 수정할 수 있습니다.");
        }

        order.update(dto.getDeliverStatus());
        return OrderResponseDto.fromEntity(order);
    }

    // 삭제
    @Transactional
    public void deleteOrder(Long orderId, OrderDeleteRequestDto dto) {
        Member member = memberRepository.findById(dto.getBuyerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 구매자입니다."));

        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        if (!orders.getBuyer().getId().equals(member.getId())) {
            throw new IllegalArgumentException("구매자 본인이 주문을 삭제할 수 있습니다.");
        }

        if (orders.getDeliverStatus() != DeliverStatus.COMPLETED) {
            throw new IllegalStateException("주문은 COMPLETED 상태일 때만 삭제할 수 있습니다.");
        }

        if (orders.getShippingAddress() != null) {
            orders.getShippingAddress().setOrder(null);
        }

        orderRepository.delete(orders);
    }
}