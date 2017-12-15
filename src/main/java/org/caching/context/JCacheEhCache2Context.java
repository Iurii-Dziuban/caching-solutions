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

import java.net.URISyntaxException;

/**
 * Created by iurii.dziuban on 04.10.2016.
 */
@Configuration
@ComponentScan(basePackages = {"org.caching.data.value", "org.caching.data.jcache_ehcache2_dao"})
@EnableCaching(proxyTargetClass = true)
public class JCacheEhCache2Context {

    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager jcache,JCacheCachingProvider provider) throws URISyntaxException {
        JCacheCacheManager cacheManager = new JCacheCacheManager();
        cacheManager.setCacheManager(new JCacheManager(provider, jcache,
                getClass().getResource("/ehcache2.xml").toURI(), null));
        return cacheManager;
    }

    @Bean
    public JCacheCachingProvider provider() {
        return new JCacheCachingProvider();
    }

    @Bean
    public net.sf.ehcache.CacheManager jcache() {
        return net.sf.ehcache.CacheManager.create(getClass().getClassLoader().getResourceAsStream("ehcache2.xml"));
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}
