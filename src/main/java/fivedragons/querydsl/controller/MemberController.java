package fivedragons.querydsl.controller;

import fivedragons.querydsl.dto.MemberSearchCondition;
import fivedragons.querydsl.dto.MemberTeamDto;
import fivedragons.querydsl.repository.MemberJpaRepository;
import fivedragons.querydsl.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("v1/members")
    public List<MemberTeamDto> searchMembersV1(MemberSearchCondition condition) {
        return memberJpaRepository.searchByBuilder(condition);
    }

    @GetMapping("v2/members")
    public Page<MemberTeamDto> searchMembersV2(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchPageSimple(condition, pageable);
    }

    @GetMapping("v3/members")
    public Page<MemberTeamDto> searchMembersV3(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchPageComplex_2(condition, pageable);
    }
}
