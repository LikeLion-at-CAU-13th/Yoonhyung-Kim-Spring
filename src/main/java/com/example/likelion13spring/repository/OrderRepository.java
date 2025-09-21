package com.example.likelion13spring.repository;

import com.example.likelion13spring.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByBuyerId(Long buyerId);
}
