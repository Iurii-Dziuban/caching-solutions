package org.caching.context;

import com.hazelcast.cache.impl.HazelcastServerCachingProvider;
import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import javax.cache.spi.CachingProvider;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iurii.dziuban on 04.10.2016.
 */
@Configuration
@ComponentScan(basePackages = {"org.caching.data.value", "org.caching.data.jcache_hazelcast_dao"})

@EnableCaching(proxyTargetClass = true)
public class JCacheHazelcastContext {

    @Bean
    @DependsOn("instance")
    public CacheManager cacheManager(HazelcastInstance instance) {
        // Only one in classpath solution Caching.getCachingProvider();
        CachingProvider cachingProvider = HazelcastServerCachingProvider.createCachingProvider(instance);
        javax.cache.CacheManager cacheManager = cachingProvider.getCacheManager();
        return new JCacheCacheManager(cacheManager);
    }

    @Bean
    public Config config(){
        Map<String, CacheSimpleConfig> cacheConfigs = new HashMap<String, CacheSimpleConfig>();
        cacheConfigs.put("jcachehazelcasttransactions", new CacheSimpleConfig().setName("jcachehazelcasttransactions"));
        Config config = new Config().setCacheConfigs(cacheConfigs);
        return config;
    }

    @Bean
    public HazelcastInstance instance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}
