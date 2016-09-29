package org.caching.data.spring_dao;

import org.caching.data.GeneralTransactionDao;
import org.caching.data.value.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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
public class TransactionDao implements GeneralTransactionDao{

    @Autowired
    private CacheManager cacheManager;

    private final List<Transaction> transactions;

    public TransactionDao() {
        this.transactions = new ArrayList<Transaction>();
    }

    @CachePut(cacheNames = "transactions", key = "#transaction.id")
    @Override
    public void saveWithCache(Transaction transaction) throws InterruptedException {
        saveWithoutCache(transaction);
    }

    @Override
    public void saveWithoutCache(Transaction transaction) throws InterruptedException {
        // Emulate time for saving
        Thread.sleep(4000);
        transactions.add(transaction);
    }

    @Cacheable("transactions")
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

    @CacheEvict(cacheNames = "transactions", key = "#transaction.id", beforeInvocation = true)
    @Override
    public void removeWithCache(Transaction transaction) {
        cacheManager.getCache("transactions").clear();
        removeWithoutCache(transaction);
    }

    @Override
    public void removeWithoutCache(Transaction transaction) {
        transactions.remove(transaction);
    }
}
