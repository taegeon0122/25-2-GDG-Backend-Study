package com.example.shop.order;

import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.dto.OrderUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 애노테이션 생성해야 저장소 사용 가능
public class OrderService {
    // 저장소 필요
    private final OrderRepository orderRepository;

    // @Transactional
    public Long createOrder(OrderCreateRequest request) {
        Order existingOrder = orderRepository.findByOrderId(request.getOrderId());
        if (existingOrder != null) {
            throw new RuntimeException("Order already exists" + request.getOrderId());
        }

        Order order = new Order(
                request.getOrderId(),
                request.getOrderDate(),
                request.getStatus()
        );

        orderRepository.save(order);
        return order.getId();
    }

    // get은 조회이므로 정보 상의 수정을 막기 위해 "읽기 전용으로 설정"
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id);
        // 분기 처리
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        return order;
    }

    // @Transactional
    public void updateOrder(Long id, OrderUpdateRequest request) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        // 회원 정보 수정(도메인 객체의 메서드 사용)
        order.updateInfo(request.getOrderDate(), request.getStatus());
    }

    // @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        // 회원 정보 삭제
        orderRepository.deleteById(id);
    }
}
