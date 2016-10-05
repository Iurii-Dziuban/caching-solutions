package org.caching.context;


import org.jboss.cache.Cache;
import org.jboss.cache.DefaultCacheFactory;
import org.jboss.cache.config.EvictionConfig;
import org.jboss.cache.transaction.JBossTransactionManagerLookup;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iurii.dziuban on 30.09.2016.
 */
@Configuration
@ComponentScan(basePackages = {"org.caching.data.value", "org.caching.data.jboss_dao"})
public class JbossCachingContext {

    @Bean(destroyMethod="stop")
    public <String,Transaction> Cache<String, Transaction> cache() {
        org.jboss.cache.config.Configuration cacheConfiguration = new org.jboss.cache.config.Configuration();
        cacheConfiguration.setCacheMode(org.jboss.cache.config.Configuration.CacheMode.REPL_ASYNC);
        cacheConfiguration.setTransactionManagerLookupClass(JBossTransactionManagerLookup.class.getName());
        cacheConfiguration.setClusterName("cacheClusterName");
        cacheConfiguration.setEvictionConfig(new EvictionConfig());
        return new DefaultCacheFactory<String, Transaction>().createCache(cacheConfiguration, true);
    }

}
