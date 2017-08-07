package org.caching.data.jboss_dao;

import org.caching.data.GeneralTransactionDao;
import org.caching.data.lombok.Transaction;
import org.jboss.cache.Cache;
import org.jboss.cache.Fqn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@Repository
public class TransactionJbossDao implements GeneralTransactionDao {

    private static final int MILLISECONDS_WAIT = 4000;
    private final Map<String, Transaction> transactions;

    @Autowired
    private Cache<String, Transaction> jbossCache;

    public TransactionJbossDao() {
        this.transactions = new HashMap<>();
    }

    @Override
    public Transaction saveWithCache(String key, Transaction transaction) throws InterruptedException {
        jbossCache.put(new Fqn<Object>("jbosstransactions"), transaction.getName(), transaction);
        saveWithoutCache(transaction.getName(), transaction);
        return transaction;
    }

    @Override
    public Transaction saveWithoutCache(String key, Transaction transaction) throws InterruptedException {
        // Emulate time for saving
        Thread.sleep(MILLISECONDS_WAIT);
        transactions.put(transaction.getName(), transaction);
        return transaction;
    }

    @Override
    public Transaction findByName(final String name) throws InterruptedException {
        Transaction cachedValue = jbossCache.get(new Fqn<Object>("jbosstransactions"), name);
        if (cachedValue != null) {
            return cachedValue;
        }
        // Emulate time for searching
        Thread.sleep(MILLISECONDS_WAIT);
        Transaction transaction = transactions.get(name);
        if (transaction != null) {
            jbossCache.put(new Fqn<Object>("jbosstransactions"), transaction.getName(), transaction);
        }
        return transaction;
    }

    @Override
    public void removeWithCache(Transaction transaction) {
        jbossCache.evict(new Fqn<Object>("jbosstransactions"),true);

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
        jbossCache.removeNode(new Fqn<Object>("jbosstransactions"));
    }
}
