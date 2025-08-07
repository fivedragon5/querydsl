package fivedragons.querydsl.controller;

import fivedragons.querydsl.dto.MemberSearchCondition;
import fivedragons.querydsl.dto.MemberTeamDto;
import fivedragons.querydsl.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;

    @GetMapping("v1/members")
    public List<MemberTeamDto> searchMembersV1(MemberSearchCondition condition) {
        return memberJpaRepository.searchByBuilder(condition);
    }
}
