package fivedragons.querydsl.repository;

import fivedragons.querydsl.entity.Member;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired MemberRepository MemberRepository;

    @Test
    void basicTest() {
        Member member = new Member("member1", 10);
        MemberRepository.save(member);

        Member findMember = MemberRepository.findById(member.getId()).get();
        List<Member> result1 = MemberRepository.findAll();
        List<Member> result2 = MemberRepository.findByUsername("member1");

        Assertions.assertThat(findMember).isEqualTo(member);
        Assertions.assertThat(result1).containsExactly(member);
        Assertions.assertThat(result2).containsExactly(member);
    }
}