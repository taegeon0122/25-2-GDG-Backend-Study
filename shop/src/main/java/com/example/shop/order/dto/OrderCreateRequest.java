package com.example.shop.order.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Getter
public class OrderCreateRequest {
    private Long orderId;
    private LocalDateTime orderDate;
    private String status;
    private int totalPrice;
    private int pointUsed;
    private int cashAmount;

    public OrderCreateRequest(Long orderId, LocalDateTime orderDate, String status, int totalPrice, int pointUsed, int cashAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.pointUsed = pointUsed;
        this.cashAmount = cashAmount;
    }
}
