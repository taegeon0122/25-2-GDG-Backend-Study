package com.example.shop.order;

import com.example.shop.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Member.class)
                .getResultList();
    }

    public Order findByLoginId(String loginId) {
        List<Order> result = em.createQuery(
                "SELECT o FROM Order o WHERE o.loginId = :loginId", Order.class
        ).setParameter("loginId", loginId).getResultList();

        return result.isEmpty() ? null : result.get(0);
//        존재하면 한 명일테니 첫번째 인덱스만 return.
    }

    public void save(Order order) {
        em.persist(order);
    }

    public void deleteById(Long id) {
        Order order = em.find(Order.class, id); // 멤버 엔티티를 영속 상태로 만든다.
        em.remove(order);
    }
}
