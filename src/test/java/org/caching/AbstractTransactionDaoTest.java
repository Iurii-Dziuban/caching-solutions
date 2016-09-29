package org.caching;

import org.caching.data.GeneralTransactionDao;
import org.caching.data.value.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by iurii.dziuban on 06.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTransactionDaoTest {

    private static final String TRANSACTION_NAME = "Iurii";

    @Inject
    private GeneralTransactionDao transactionDao;
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        transaction = new Transaction();
        transaction.setId(1);
        transaction.setName(TRANSACTION_NAME);
    }

    @Test
    @DirtiesContext
    public void shouldSaveWithoutCaching() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        transactionDao.saveWithoutCache(transaction);
        long endTime = System.currentTimeMillis();
        Assert.assertTrue(endTime - startTime > 2000);

        startTime = System.currentTimeMillis();
        transactionDao.saveWithoutCache(transaction);
        endTime = System.currentTimeMillis();
        Assert.assertTrue(endTime - startTime > 2000);
    }

    @Test
    @DirtiesContext
    public void shouldCachePutAnnotationImprovePerformanceForFindMethod() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        transactionDao.saveWithCache(transaction);
        long endTime = System.currentTimeMillis();
        Assert.assertTrue(endTime - startTime > 2000);

        startTime = System.currentTimeMillis();
        transactionDao.saveWithCache(transaction);
        endTime = System.currentTimeMillis();
        Assert.assertTrue(endTime - startTime > 2000);
    }

    @Test
    @DirtiesContext
    public void shouldFindTransaction() throws InterruptedException {
        transactionDao.saveWithCache(transaction);
        long startTime = System.currentTimeMillis();
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        long endTime = System.currentTimeMillis();

        Assert.assertEquals(TRANSACTION_NAME, foundTransaction.getName());
        Assert.assertTrue(endTime - startTime > 2000);
    }

    @Test
    @DirtiesContext
    public void shouldNotFindTransaction() throws InterruptedException {
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        Assert.assertNull(foundTransaction);
    }

    @Test
    public void shouldCacheEvictWorkProperly() throws InterruptedException {
        transactionDao.saveWithCache(transaction);
        long startTime = System.currentTimeMillis();
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        long endTime = System.currentTimeMillis();
        Assert.assertTrue(endTime - startTime > 2000);

        transactionDao.removeWithCache(transaction);

        startTime = System.currentTimeMillis();
        foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        endTime = System.currentTimeMillis();
        Assert.assertTrue(endTime - startTime > 2000);
    }
}
