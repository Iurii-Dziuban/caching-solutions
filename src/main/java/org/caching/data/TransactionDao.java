package org.caching.data;

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
public class TransactionDao {

    private final List<Transaction> transactions;

    public TransactionDao() {
        this.transactions = new ArrayList<Transaction>();
    }

    @CachePut(cacheNames = "transactions", key = "#transaction.id")
    public void saveWithCache(Transaction transaction) {
        saveWithoutCache(transaction);
    }

    public void saveWithoutCache(Transaction transaction) {
        transactions.add(transaction);
    }

    @Cacheable("transactions")
    public Transaction findByName(final String name) throws InterruptedException {
        Thread.sleep(3000);
        for (Transaction transaction : transactions) {
            if (name.equals(transaction.getName())) {
                return transaction;
            }
        }
        return null;
    }

    @CacheEvict(cacheNames = "transactions", key = "#transaction.id")
    public void removeWithCache(Transaction transaction) {
        removeWithoutCache(transaction);
    }

    public void removeWithoutCache(Transaction transaction) {
        transactions.remove(transaction);
    }
}
