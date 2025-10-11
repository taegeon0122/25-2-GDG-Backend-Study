package com.example.shop.member.dto;

// password, phoneNumber, address

import lombok.Getter;

@Getter
public class MemberUpdateRequest {
    private String password, phoneNumber, address;

    public MemberUpdateRequest(String password, String phoneNumber, String address) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}