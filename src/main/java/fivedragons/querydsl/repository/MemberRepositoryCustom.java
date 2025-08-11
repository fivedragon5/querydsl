package fivedragons.querydsl.repository;

import fivedragons.querydsl.dto.MemberSearchCondition;
import fivedragons.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
}
