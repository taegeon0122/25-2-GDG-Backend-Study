// Controller : 웨이터, Service에게 시키는 역할
package com.example.shop.member;

import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
@Controller
@ResponseBody
// 메서드 반환 값을 http body에 작성하게 해주는 애노테이션
*/
@RestController
// @Controller, @ResponseBody 두 개를 묶음

@RequiredArgsConstructor
// 모든 필드값을 파라미터로 받는 생성자 생성

@RequestMapping("/members")

public class MemberController {

    private final MemberService memberService;
//    회원 등록
    @PostMapping//("/members") // endpoint 명시 > @RequestMapping("/members") 애노테이션으로 생략 가능
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
        Long memberId = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
//        Service 계층에서 회원 목록을 가져온다.
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);  // ok : 바디 안에 members를 넣겠다.
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable("memberId") Long memberId) { // @PathVariable : 위의 {memberId}를 가져오겠다
        Member member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long memberId,
            MemberUpdateRequest request) {
        memberService.updateMember(memberId, request);
        return ResponseEntity.ok().build();
//        200 ok 형태로 return 하고, body가 비어있기 때문에 <Void> 형태임.
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build(); // 284 no content
    }
}
