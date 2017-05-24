package org.caching;

import org.caching.data.GeneralTransactionDao;
import org.caching.data.value.generated.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

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
        transactionDao.saveWithoutCache(transaction.getName(), transaction);
        long endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > 2000);

        startTime = System.currentTimeMillis();
        transactionDao.saveWithoutCache(transaction.getName(), transaction);
        endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > 2000);
    }

    @Test
    @DirtiesContext
    public void shouldCachePutAnnotationImprovePerformanceForFindMethod() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        transactionDao.saveWithCache(transaction.getName(), transaction);
        long endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > 2000).isTrue();

        startTime = System.currentTimeMillis();
        transactionDao.saveWithCache(transaction.getName(), transaction);
        endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > 2000).isTrue();
    }

    @Test
    @DirtiesContext
    public void shouldFindTransaction() throws InterruptedException {
        transactionDao.saveWithCache(transaction.getName(), transaction);
        long startTime = System.currentTimeMillis();
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        long endTime = System.currentTimeMillis();

        assertThat(foundTransaction.getName()).isEqualTo(TRANSACTION_NAME);
        assertThat(endTime - startTime < 2000).isTrue();
    }

    @Test
    @DirtiesContext
    public void shouldNotFindTransaction() throws InterruptedException {
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        assertThat(foundTransaction).isNull();
    }

    @Test
    public void shouldCacheEvictWorkProperly() throws InterruptedException {
        transactionDao.saveWithCache(transaction.getName(), transaction);
        long startTime = System.currentTimeMillis();
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        long endTime = System.currentTimeMillis();
        assertThat(endTime - startTime < 2000).isTrue();

        transactionDao.clearCache();

        startTime = System.currentTimeMillis();
        foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > 2000).isTrue();
    }
}
