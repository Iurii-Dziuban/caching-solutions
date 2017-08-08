package org.caching;

import org.caching.context.JCacheEhCacheContext;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@ContextConfiguration(classes = JCacheEhCacheContext.class)
public class JCacheEhCacheTransactionDaoTest extends AbstractTransactionDaoTest{

    @Inject
    private CacheManager cacheManager;

    @Inject
    private KeyGenerator keyGenerator;
}