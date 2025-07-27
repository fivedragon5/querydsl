package fivedragons.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fivedragons.querydsl.entity.Member;
import fivedragons.querydsl.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static fivedragons.querydsl.entity.QMember.*;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void setUp() {
        queryFactory = new JPAQueryFactory(em);
        // given
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    void startJPQL() {
        Member member1 = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        Assertions.assertEquals(member1.getUsername(), "member1");
    }

    @Test
    void startQuerydsl() {
        Member member1 = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        Assertions.assertEquals(member1.getUsername(), "member1");
    }

    @Test
    void search() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.goe(10)))
                .fetchOne();

        Assertions.assertEquals(findMember.getUsername(), "member1");
    }

    @Test
    void searchAndParam() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1"),
                        member.age.goe(10)
                )
                .fetchOne();

        Assertions.assertEquals(findMember.getUsername(), "member1");
    }
}
