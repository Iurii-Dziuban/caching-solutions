package org.caching;

import org.caching.context.JCacheEhCache3Context;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@ContextConfiguration(classes = JCacheEhCache3Context.class)
public class JCacheEhCache3TransactionDaoTest extends AbstractTransactionDaoTest{

    @Inject
    private CacheManager cacheManager;

}