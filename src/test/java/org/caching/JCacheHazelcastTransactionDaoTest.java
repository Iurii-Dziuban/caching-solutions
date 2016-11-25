package org.caching;

import org.caching.context.JCacheHazelcastContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@ContextConfiguration(classes = JCacheHazelcastContext.class)
public class JCacheHazelcastTransactionDaoTest extends AbstractTransactionDaoTest{

}