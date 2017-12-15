package org.caching;

import org.caching.context.EhCache2ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@ContextConfiguration(classes = EhCache2ApplicationContext.class)
public class Ehcache2TransactionDaoTest extends AbstractTransactionDaoTest{

}
