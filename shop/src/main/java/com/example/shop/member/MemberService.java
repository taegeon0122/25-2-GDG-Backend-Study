// Service : 요리하는 셰프인데 재료가 없음
package com.example.shop.member;

import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 애노테이션 생성해야 저장소 사용 가능
public class MemberService {
//    저장소 필요
    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(MemberCreateRequest request) {
        Member existingMember = memberRepository.findByLoginId(request.getLoginId());
        if (existingMember != null) {
            throw new RuntimeException("Member already exists" +  request.getLoginId());
        }

        Member member = new Member(
                request.getLoginId(),
                request.getPassword(),
                request.getAddress(),
                request.getPhoneNumber()
        );

        memberRepository.save(member);
        return member.getId();
    }

//    get은 조회이므로 정보 상의 수정을 막기 위해 "읽기 전용으로 설정"
    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        Member member = memberRepository.findById(id);
//        분기 처리
        if(member == null) {
            throw new RuntimeException("Member not found");
        }
        return member;
    }

    @Transactional
    public void updateMember(Long id, MemberUpdateRequest request) {
        Member member = memberRepository.findById(id);

        if(member == null) {
            throw new RuntimeException("Member not found");
        }
//        회원 정보 수정(도메인 객체의 메서드 사용)
        member.updateInfo(request.getPassword(), request.getPhoneNumber(), request.getAddress());
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id);

        if(member == null) {
            throw new RuntimeException("Member not found");
        }
//        회원 정보 삭제
        memberRepository.deleteById(id);
    }
}
