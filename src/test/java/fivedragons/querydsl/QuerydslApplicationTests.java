package fivedragons.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fivedragons.querydsl.entity.Hello;
import fivedragons.querydsl.entity.QHello;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		entityManager.persist(hello);

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QHello qHello = QHello.hello;

		Hello result = queryFactory
				.selectFrom(qHello)
				.fetchOne();

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result, hello);
		Assertions.assertEquals(result.getId(), hello.getId());
	}
}
