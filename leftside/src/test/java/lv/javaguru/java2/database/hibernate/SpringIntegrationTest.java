package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.web.spring.SpringAppConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by MuromcevS on 06/12/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@TransactionConfiguration(defaultRollback = false)
public class SpringIntegrationTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }
}