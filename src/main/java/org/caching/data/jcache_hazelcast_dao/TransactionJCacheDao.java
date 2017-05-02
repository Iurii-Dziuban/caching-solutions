package org.caching.data.jcache_hazelcast_dao;

import org.caching.data.GeneralTransactionDao;
import org.caching.data.value.generated.Transaction;
import org.springframework.stereotype.Repository;

import javax.cache.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@CacheDefaults(cacheName = "jcachehazelcasttransactions")
@Repository
public class TransactionJCacheDao implements GeneralTransactionDao {

    private final List<Transaction> transactions;

    public TransactionJCacheDao() {
        this.transactions = new ArrayList<Transaction>();
    }

    @CachePut
    @Override
    public Transaction saveWithCache(@CacheKey String key, @CacheValue Transaction transaction) throws InterruptedException {
        return saveWithoutCache(key, transaction);
    }

    @Override
    public Transaction saveWithoutCache(String key, Transaction transaction) throws InterruptedException {
        // Emulate time for saving
        Thread.sleep(4000);
        transactions.add(transaction);
        return transaction;
    }

    @CacheResult
    @Override
    public Transaction findByName(@CacheKey final String name) throws InterruptedException {
        // Emulate time for searching
        Thread.sleep(4000);
        for (Transaction transaction : transactions) {
            if (name.equals(transaction.getName())) {
                return transaction;
            }
        }
        return null;
    }

    @CacheRemove(afterInvocation = true)
    @Override
    public void removeWithCache(Transaction transaction) {
        removeWithoutCache(transaction);
    }

    @Override
    public void removeWithoutCache(Transaction transaction) {
        transactions.remove(transaction);
    }

    @CacheRemoveAll
    @Override
    public void clearCache() {
    }
}