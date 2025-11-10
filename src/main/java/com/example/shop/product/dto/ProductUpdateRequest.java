package com.example.shop.product.dto;

import lombok.Getter;

@Getter
public class ProductUpdateRequest {
    private Long productId;
    private String productName;
    private Long productNum;
    private Long loginId;

    public ProductUpdateRequest(Long productId, String productName, Long productNum, Long loginId) {
        this.productId = productId;
        this.productName = productName;
        this.productNum = productNum;
        this.loginId = loginId;
    }
}