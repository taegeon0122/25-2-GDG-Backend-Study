package com.example.shop.order;

import com.example.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_Id")
    private Long id;

    //    해당 외래키로 생기는 연관관계가 무엇인지 나타냄. 여기서는 다대일
    @ManyToOne(fetch = FetchType.LAZY) // LAZY(지연 로딩, 필요할 때만 가져온다), EAGER(즉시 로딩)
    //    FK 컬럼 정보를 명시(c.f. name)
    @JoinColumn(name = "member_id")
    private Member member;
//    PK : 엔티티 객체를 필드로 넣는다! -> ORM(JPA)가 알아서 처리

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "point_use")
    private int pointUsed;

    @Column(name = "cash_amount")
    private int cashAmount;

    @Column(name = "status", length = 25)
    private String status;

    public Order(Member member, Long id, LocalDateTime orderDate, int totalPrice, int pointUsed, int cashAmount, String status) {
        this.member = member;
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.pointUsed = pointUsed;
        this.cashAmount = cashAmount;
        this.status = status;
    }

    public Order(Long orderId, LocalDateTime orderDate, String status) {
    }

    public void updateInfo(LocalDateTime orderDate, String status) {
        this.orderDate = orderDate;
        this.status = status;
    }
}
