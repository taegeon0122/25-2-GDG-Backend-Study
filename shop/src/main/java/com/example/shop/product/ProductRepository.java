package com.example.shop.product;

import com.example.shop.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager em;

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Member.class)
                .getResultList();
    }

    public Product findByLoginId(String loginId) {
        List<Product> result = em.createQuery(
                "SELECT p FROM Product p WHERE p.loginId = :loginId", Product.class
        ).setParameter("loginId", loginId).getResultList();

        return result.isEmpty() ? null : result.get(0);
//        존재하면 한 명일테니 첫번째 인덱스만 return.
    }

    public void save(Product product) {
        em.persist(product);
    }

    public void deleteById(Long id) {
        Product product = em.find(Product.class, id); // 멤버 엔티티를 영속 상태로 만든다.
        em.remove(product);
    }
}
