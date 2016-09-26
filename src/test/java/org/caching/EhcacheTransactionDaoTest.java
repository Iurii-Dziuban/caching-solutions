package org.caching;

import org.caching.context.EhCacheApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@ContextConfiguration(classes = EhCacheApplicationContext.class)
public class EhcacheTransactionDaoTest extends AbstractTransactionDaoTest{

}
