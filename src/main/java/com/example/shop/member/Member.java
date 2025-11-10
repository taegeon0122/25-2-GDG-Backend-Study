package com.example.shop.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
public class Member {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name="member_logId", length=50)

    private String loginId;

    @Column(name="member_password", length=100)
    private String password;

    @Column(name="member_phone", length=20)
    private String phoneNumber;

    @Column(name="member_address", length=255)
    private String address;

    @Column(name="member_point")
    private int point;

    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0;
    }

//    loginId는 변경 불가
    public void updateInfo(String password, String phoneNumber, String address) {
        if(password != null) {this.password = password;}
        if(password != null) {this.phoneNumber = phoneNumber;}
        if(password != null) {this.address = address;}

    }
}
