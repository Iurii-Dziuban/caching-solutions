package org.caching.data.jcache_dao;

import net.sf.ehcache.CacheManager;
import org.caching.data.GeneralTransactionDao;
import org.caching.data.lombok.Transaction;
import org.springframework.stereotype.Repository;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@CacheDefaults(cacheName = "jcachetransactions")
@Repository
public class TransactionJCacheDao implements GeneralTransactionDao {

    private static final int MILLISECONDS_WAIT = 4000;
    private final Map<String, Transaction> transactions;

    public TransactionJCacheDao() {
        this.transactions = new HashMap<>();
    }

    @CachePut
    @Override
    public Transaction saveWithCache(@CacheKey String key, @CacheValue Transaction transaction)
            throws InterruptedException {
        return saveWithoutCache(key, transaction);
    }

    @Override
    public Transaction saveWithoutCache(String key, Transaction transaction) throws InterruptedException {
        // Emulate time for saving
        Thread.sleep(MILLISECONDS_WAIT);
        transactions.put(transaction.getName(), transaction);
        return transaction;
    }

    @CacheResult
    @Override
    public Transaction findByName(@CacheKey final String name) throws InterruptedException {
        // Emulate time for searching
        Thread.sleep(MILLISECONDS_WAIT);
        return transactions.get(name);
    }

    @CacheRemove(afterInvocation = false)
    @Override
    public void removeWithCache(Transaction transaction) {
    }

    @CacheRemove(afterInvocation = false)
    @Override
    public void removeWithCache(@CacheKey final String name) {
        removeWithoutCache(name);
    }

    @Override
    public void removeWithoutCache(Transaction transaction) {
    }

    @Override
    public void removeWithoutCache(String name) {
        transactions.remove(name);
    }

    @CacheRemoveAll
    @Override
    public void clearCache() {
        CacheManager.getInstance().clearAll();
    }
}
