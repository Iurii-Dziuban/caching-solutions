package org.caching.data.ehcache2_dao;

import net.sf.ehcache.CacheManager;
import org.caching.data.GeneralTransactionDao;
import org.caching.data.lombok.Transaction;
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
public class TransactionEhcache2Dao implements GeneralTransactionDao {

    private static final int MILLISECONDS_WAIT = 4000;
    private final Map<String, Transaction> transactions;

    public TransactionEhcache2Dao() {
        this.transactions = new HashMap<>();
    }

    @CachePut(cacheNames = "ehtransactions", key = "#transaction.name")
    @Override
    public Transaction saveWithCache(String key, Transaction transaction) throws InterruptedException {
        return saveWithoutCache(transaction.getName(), transaction);
    }

    @Override
    public Transaction saveWithoutCache(String key, Transaction transaction) throws InterruptedException {
        // Emulate time for saving
        Thread.sleep(MILLISECONDS_WAIT);
        transactions.put(transaction.getName(), transaction);
        return transaction;
    }

    @Cacheable(cacheNames = "ehtransactions", key ="#name")
    @Override
    public Transaction findByName(final String name) throws InterruptedException {
        // Emulate time for searching
        Thread.sleep(MILLISECONDS_WAIT);
        return transactions.get(name);
    }

    @CacheEvict(cacheNames = "ehtransactions", key = "#transaction.name", beforeInvocation = true)
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
    public void clearCache() {
        CacheManager.getInstance().clearAll();
    }
}
