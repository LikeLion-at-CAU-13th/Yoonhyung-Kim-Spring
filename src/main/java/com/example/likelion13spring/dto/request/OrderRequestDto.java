package com.example.likelion13spring.dto.request;

import com.example.likelion13spring.domain.Mapping.ProductOrders;
import com.example.likelion13spring.domain.Member;
import com.example.likelion13spring.domain.Orders;
import com.example.likelion13spring.domain.Product;
import com.example.likelion13spring.domain.ShippingAddress;
import com.example.likelion13spring.enums.DeliverStatus;
import lombok.Getter;

@Getter
public class OrderRequestDto {
    private Long buyerId;
    private String recipient;
    private String phoneNumber;
    private String streetAddress;
    private String detailAddress;
    private String postNumber;
    private DeliverStatus deliverStatus;

    public Orders toEntity(Member buyer) {
        ShippingAddress address = ShippingAddress.builder()
                .recipient(this.recipient)
                .phoneNumber(this.phoneNumber)
                .streetAddress(this.streetAddress)
                .detailAddress(this.detailAddress)
                .postNumber(this.postNumber)
                .build();

        Orders orders = Orders.builder()
                .buyer(buyer)
                .deliverStatus(this.deliverStatus != null ? this.deliverStatus : DeliverStatus.PREPARATION)
                .shippingAddress(address)
                .build();

        address.setOrder(orders);

        return orders;
    }
}
