package org.caching.data;

import org.caching.data.lombok.Transaction;

/**
 * Created by iurii.dziuban on 27.09.2016.
 */
public interface GeneralTransactionDao {

    Transaction saveWithCache(String key, Transaction transaction) throws InterruptedException;

    Transaction saveWithoutCache(String key, Transaction transaction) throws InterruptedException;

    Transaction findByName(final String name) throws InterruptedException;

    void removeWithCache(Transaction transaction);

    void removeWithCache(String name);

    void removeWithoutCache(Transaction transaction);

    void removeWithoutCache(String name);

    void clearCache();
}
