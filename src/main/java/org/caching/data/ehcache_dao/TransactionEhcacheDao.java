package org.caching.data.ehcache_dao;

import net.sf.ehcache.CacheManager;
import org.caching.data.GeneralTransactionDao;
import org.caching.data.value.generated.Transaction;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@Repository
public class TransactionEhcacheDao implements GeneralTransactionDao {

    private final List<Transaction> transactions;

    public TransactionEhcacheDao() {
        this.transactions = new ArrayList<Transaction>();
    }

    @CachePut(cacheNames = "ehtransactions", key = "#transaction.name")
    @Override
    public Transaction saveWithCache(String key, Transaction transaction) throws InterruptedException {
        return saveWithoutCache(key, transaction);
    }

    @Override
    public Transaction saveWithoutCache(String key, Transaction transaction) throws InterruptedException {
        // Emulate time for saving
        Thread.sleep(4000);
        transactions.add(transaction);
        return transaction;
    }

    @Cacheable(value = "ehtransactions", key ="#name")
    @Override
    public Transaction findByName(final String name) throws InterruptedException {
        // Emulate time for searching
        Thread.sleep(4000);
        for (Transaction transaction : transactions) {
            if (name.equals(transaction.getName())) {
                return transaction;
            }
        }
        return null;
    }

    @CacheEvict(cacheNames = "ehtransactions", key = "#transaction.name", beforeInvocation = true)
    @Override
    public void removeWithCache(Transaction transaction) {
        removeWithoutCache(transaction);
    }

    @Override
    public void removeWithoutCache(Transaction transaction) {
        transactions.remove(transaction);
    }

    @Override
    public void clearCache() {
        CacheManager.getInstance().clearAll();
    }
}
