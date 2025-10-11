// dto : 영수증 역할
package com.example.shop.member.dto;

// id, loginId, password, phoneNumber, address 가 필요

import lombok.Getter;

@Getter
public class MemberCreateRequest {
    private String loginId, password, phoneNumber, address;

    public MemberCreateRequest(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
