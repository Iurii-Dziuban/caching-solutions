package org.caching;

import org.caching.context.EhCacheApplicationContext;
import org.caching.context.JCacheEhCacheContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@ContextConfiguration(classes = JCacheEhCacheContext.class)
public class JCacheTransactionDaoTest extends AbstractTransactionDaoTest{

}