package com.example.likelion13spring.domain;

import com.example.likelion13spring.domain.Mapping.ProductOrders;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String recipient; // 수령인
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String streetAddress;
    @Column(nullable = false)
    private String detailAddress;
    @Column(nullable = false)
    private String postNumber;

    @Setter
    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    public void update(String recipient, String phoneNumber, String streetAddress, String detailAddress, String postNumber) {
        this.recipient = recipient;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.detailAddress = detailAddress;
        this.postNumber = postNumber;
    }
}
