package org.caching.data.spring_dao;

import org.caching.data.GeneralTransactionDao;
import org.caching.data.lombok.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@Repository
public class TransactionDao implements GeneralTransactionDao{

    private static final int MILLISECONDS_WAIT = 4000;

    @Autowired
    private CacheManager cacheManager;

    private final Map<String, Transaction> transactions;

    public TransactionDao() {
        this.transactions = new HashMap<>();
    }

    @CachePut(cacheNames = "transactions", key = "#transaction.name")
    @Override
    public Transaction saveWithCache(String key, Transaction transaction) throws InterruptedException {
        saveWithoutCache(key, transaction);
        return transaction;
    }

    @Cacheable(cacheNames = "transactions", key = "#name")
    @Override
    public Transaction findByName(final String name) throws InterruptedException {
        // Emulate time for searching
        Thread.sleep(MILLISECONDS_WAIT);
        return transactions.get(name);
    }

    @CacheEvict(cacheNames = "transactions", key = "#transaction.name", beforeInvocation = true)
    @Override
    public void removeWithCache(Transaction transaction) {
        removeWithoutCache(transaction);
    }

    @Override
    public void removeWithCache(String name) {
    }

    @Override
    public void removeWithoutCache(Transaction transaction) {
        transactions.remove(transaction.getName());
    }

    @Override
    public void removeWithoutCache(String name) {
    }

    @Override
    public Transaction saveWithoutCache(String key, Transaction transaction) throws InterruptedException {
        // Emulate time for saving
        Thread.sleep(MILLISECONDS_WAIT);
        transactions.put(transaction.getName(), transaction);
        return transaction;
    }

    @Override
    public void clearCache() {
        cacheManager.getCache("transactions").clear();
    }
}
