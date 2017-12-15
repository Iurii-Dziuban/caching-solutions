package org.caching;

import org.caching.context.JCacheEhCache2Context;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@ContextConfiguration(classes = JCacheEhCache2Context.class)
public class JCacheEhCache2TransactionDaoTest extends AbstractTransactionDaoTest{

    @Inject
    private CacheManager cacheManager;

    @Inject
    private KeyGenerator keyGenerator;
}