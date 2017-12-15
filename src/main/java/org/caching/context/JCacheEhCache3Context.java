package org.caching.context;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URISyntaxException;

/**
 * Created by iurii.dziuban on 04.10.2016.
 */
@Configuration
@ComponentScan(basePackages = {"org.caching.data.value", "org.caching.data.jcache_ehcache3_dao"})
@EnableCaching(proxyTargetClass = true)
public class JCacheEhCache3Context {

    @Bean
    public CacheManager cacheManager() throws URISyntaxException {
        CachingProvider cachingProvider = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
        javax.cache.CacheManager cacheManager = cachingProvider.
                getCacheManager(getClass().getResource("/ehcache3.xml").toURI(), JCacheEhCache3Context.class.getClassLoader());
        return new JCacheCacheManager(cacheManager);
    }
}
