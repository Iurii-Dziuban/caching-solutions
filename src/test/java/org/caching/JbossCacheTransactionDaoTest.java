package org.caching;

import org.caching.context.JbossCachingContext;
import org.caching.context.SpringCachingContext;
import org.jboss.cache.Cache;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@ContextConfiguration(classes = JbossCachingContext.class)
public class JbossCacheTransactionDaoTest extends AbstractTransactionDaoTest{

}