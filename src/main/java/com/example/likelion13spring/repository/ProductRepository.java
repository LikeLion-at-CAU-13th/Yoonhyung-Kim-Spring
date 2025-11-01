package com.example.likelion13spring.repository;

import com.example.likelion13spring.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller_Name(String username);
}
