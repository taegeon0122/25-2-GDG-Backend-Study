package com.example.shop.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

//    복잡한 조회에는 JPQL 사용
    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    public Member findByLoginId(String loginId) {
        List<Member> result = em.createQuery(
                "SELECT m FROM Member m WHERE m.loginId = :loginId", Member.class
//                멤버 엔티티 중에서 로그인 아이디 필드가 특정 파라미터와 일치하는 회원 조회 -> 변수 앞에 : 붙이면 파라미터 된다.
        ).setParameter("loginId", loginId).getResultList();

        return result.isEmpty() ? null : result.get(0);
//        존재하면 한 명일테니 첫번째 인덱스만 return.
    }

    public void save(Member member) {
        em.persist(member);
    }

    public void deleteById(Long id) {
        Member member = em.find(Member.class, id); // 멤버 엔티티를 영속 상태로 만든다.
        em.remove(member);
    }
}
