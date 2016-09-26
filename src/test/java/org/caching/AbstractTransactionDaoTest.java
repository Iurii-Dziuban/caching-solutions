package org.caching;

import org.caching.context.EhCacheApplicationContext;
import org.caching.data.Transaction;
import org.caching.data.TransactionDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EhCacheApplicationContext.class)
public abstract class AbstractTransactionDaoTest {

    private static final String TRANSACTION_NAME = "Iurii";

    @Inject
    private TransactionDao transactionDao;
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        transaction = new Transaction();
        transaction.setId(1);
        transaction.setName(TRANSACTION_NAME);
    }

    @Test
    @DirtiesContext
    public void shouldCachingAnnotationImprovePerformanceForFindMethod() throws InterruptedException {
        transactionDao.saveWithoutCache(transaction);
    }

    @Test
    @DirtiesContext
    public void shouldCachePutAnnotationImprovePerformanceForFindMethod() throws InterruptedException {
        transactionDao.saveWithCache(transaction);
    }

    @Test
    @DirtiesContext
    public void shouldInvokeFindMethod() throws InterruptedException {
        transactionDao.saveWithCache(transaction);
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        Assert.assertEquals(TRANSACTION_NAME, foundTransaction.getName());
    }

    @Test
    public void shouldCacheEvictAnnotationImprovePerformanceForFindMethod() throws InterruptedException {
        String secondtransactionName = "Michal";
        Transaction secondTransaction = new Transaction();
        secondTransaction.setName(secondtransactionName);
        secondTransaction.setId(2);

        transactionDao.saveWithCache(transaction);
        transactionDao.saveWithCache(secondTransaction);

        transactionDao.removeWithoutCache(transaction);
        transactionDao.removeWithCache(secondTransaction);
    }
}
