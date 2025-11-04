package com.example.shop.order;

import com.example.shop.member.MemberService;
import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.net.URI;
import java.util.List;

@RestController
// @Controller, @ResponseBody 두 개를 묶음

@RequiredArgsConstructor
// 모든 필드값을 파라미터로 받는 생성자 생성

@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    //    회원 등록
    @PostMapping//("/members") // endpoint 명시 > @RequestMapping("/members") 애노테이션으로 생략 가능
    public ResponseEntity<Void> createOrder(@RequestBody OrderCreateRequest request) {
        Long orderId = orderService.createOrder(request);
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

    @GetMapping
    public ResponseEntity<List<java.lang.reflect.Order>> getAllOrders() {
//        Service 계층에서 회원 목록을 가져온다.
        List<java.lang.reflect.Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);  // ok : 바디 안에 members를 넣겠다.
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<java.lang.reflect.Order> getOrder(@PathVariable("orderId") Long orderId) { // @PathVariable : 위의 {memberId}를 가져오겠다
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(
            @PathVariable Long orderId,
            OrderUpdateRequest request) {
        orderService.updateOrder(orderId, request);
        return ResponseEntity.ok().build();
//        200 ok 형태로 return 하고, body가 비어있기 때문에 <Void> 형태임.
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build(); // 284 no content
    }
}
