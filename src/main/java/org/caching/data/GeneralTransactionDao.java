package org.caching.data;

import org.caching.data.value.Transaction;
/**
 * Created by iurii.dziuban on 27.09.2016.
 */
public interface GeneralTransactionDao {

    void saveWithCache(Transaction transaction) throws InterruptedException;

    void saveWithoutCache(Transaction transaction) throws InterruptedException;

    Transaction findByName(final String name) throws InterruptedException;

    void removeWithCache(Transaction transaction);

    void removeWithoutCache(Transaction transaction);

}
