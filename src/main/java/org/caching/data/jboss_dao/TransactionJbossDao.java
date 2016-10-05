package org.caching.data.jboss_dao;

import org.caching.data.GeneralTransactionDao;
import org.caching.data.value.generated.Transaction;
import org.jboss.cache.Cache;
import org.jboss.cache.Fqn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@Repository
public class TransactionJbossDao implements GeneralTransactionDao {

    private final List<Transaction> transactions;

    @Autowired
    private Cache<String, Transaction> jbossCache;

    public TransactionJbossDao() {
        this.transactions = new ArrayList<Transaction>();
    }

    @Override
    public Transaction saveWithCache(String key, Transaction transaction) throws InterruptedException {
        jbossCache.put(new Fqn<Object>("jbosstransactions"), transaction.getName(), transaction);
        saveWithoutCache(key, transaction);
        return transaction;
    }

    @Override
    public Transaction saveWithoutCache(String key, Transaction transaction) throws InterruptedException {
        // Emulate time for saving
        Thread.sleep(4000);
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public Transaction findByName(final String name) throws InterruptedException {
        Transaction cachedValue = jbossCache.get(new Fqn<Object>("jbosstransactions"), name);
        if (cachedValue != null) {
            return cachedValue;
        }

        // Emulate time for searching
        Thread.sleep(4000);
        for (Transaction transaction : transactions) {
            if (name.equals(transaction.getName())) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public void removeWithCache(Transaction transaction) {
        jbossCache.evict(new Fqn<Object>("jbosstransactions"),true);

        removeWithoutCache(transaction);
    }

    @Override
    public void removeWithoutCache(Transaction transaction) {
        transactions.remove(transaction);
    }

    @Override
    public void clearCache() {
        jbossCache.removeNode(new Fqn<Object>("jbosstransactions"));
    }
}
