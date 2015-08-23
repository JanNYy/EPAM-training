package ua.epam.pizzaservice.repository.template;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by Anna on 06.08.2015.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/appContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ITRepositoryTestTemplate extends AbstractTransactionalJUnit4SpringContextTests {
}
