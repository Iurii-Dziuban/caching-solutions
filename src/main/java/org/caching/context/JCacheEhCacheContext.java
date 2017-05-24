package org.caching.context;

import org.ehcache.jcache.JCacheCachingProvider;
import org.ehcache.jcache.JCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iurii.dziuban on 04.10.2016.
 */
@Configuration
@ComponentScan(basePackages = {"org.caching.data.value", "org.caching.data.jcache_dao"})
@EnableCaching(proxyTargetClass = true)
public class JCacheEhCacheContext {

    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager jcache,JCacheCachingProvider provider) {
        JCacheCacheManager cacheManager = new JCacheCacheManager();
        cacheManager.setCacheManager(new JCacheManager(provider, jcache, null, null));
        return cacheManager;
    }

    @Bean
    public JCacheCachingProvider provider() {
        return new JCacheCachingProvider();
    }

    @Bean
    public net.sf.ehcache.CacheManager jcache() {
        return net.sf.ehcache.CacheManager.create();
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}
