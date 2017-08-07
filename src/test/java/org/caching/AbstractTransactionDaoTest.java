package org.caching;

import org.caching.data.GeneralTransactionDao;
import org.caching.data.lombok.Transaction;
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
    private static final int MILLISECONDS_WAIT = 2000;
    private static final int ID = 1;

    @Inject
    private GeneralTransactionDao transactionDao;
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        transaction = new Transaction();
        transaction.setId(ID);
        transaction.setName(TRANSACTION_NAME);
    }

    @Test
    @DirtiesContext
    public void shouldSaveWithoutCaching() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        transactionDao.saveWithoutCache(transaction.getName(), transaction);
        long endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > MILLISECONDS_WAIT);

        startTime = System.currentTimeMillis();
        transactionDao.saveWithoutCache(transaction.getName(), transaction);
        endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > MILLISECONDS_WAIT);
    }

    @Test
    @DirtiesContext
    public void shouldCachePutAnnotationImprovePerformanceForFindMethod() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        transactionDao.saveWithCache(transaction.getName(), transaction);
        long endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > MILLISECONDS_WAIT).isTrue();

        startTime = System.currentTimeMillis();
        transactionDao.saveWithCache(transaction.getName(), transaction);
        endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > MILLISECONDS_WAIT).isTrue();
    }

    @Test
    @DirtiesContext
    public void shouldFindTransaction() throws InterruptedException {
        transactionDao.saveWithCache(transaction.getName(), transaction);
        long startTime = System.currentTimeMillis();
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        long endTime = System.currentTimeMillis();

        assertThat(foundTransaction.getName()).isEqualTo(TRANSACTION_NAME);
        assertThat(endTime - startTime < MILLISECONDS_WAIT).isTrue();
    }

    @Test
    @DirtiesContext
    public void shouldNotFindTransaction() throws InterruptedException {
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        assertThat(foundTransaction).isNull();
    }

    @Test
    @DirtiesContext
    public void shouldCacheEvictWorkProperly() throws InterruptedException {
        transactionDao.saveWithCache(transaction.getName(), transaction);
        long startTime = System.currentTimeMillis();
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        long endTime = System.currentTimeMillis();
        assertThat(endTime - startTime < MILLISECONDS_WAIT).isTrue();
        assertThat(foundTransaction).isNotNull();

        transactionDao.clearCache();

        startTime = System.currentTimeMillis();
        foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        endTime = System.currentTimeMillis();
        assertThat(endTime - startTime > MILLISECONDS_WAIT).isTrue();
        assertThat(foundTransaction).isNotNull();
    }

    @Test
    @DirtiesContext
    public void shouldRemoveFromCacheAndNotFromCache() throws InterruptedException {
        transactionDao.saveWithCache(transaction.getName(), transaction);
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        assertThat(foundTransaction).isNotNull();

        transactionDao.removeWithCache(foundTransaction);
        transactionDao.removeWithCache(foundTransaction.getName());

        foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        assertThat(foundTransaction).isNull();
    }

    @Test
    @DirtiesContext
    public void shouldRemoveButNotFromCache() throws InterruptedException {
        transactionDao.saveWithoutCache(transaction.getName(), transaction);
        Transaction foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        assertThat(foundTransaction).isNotNull();

        transactionDao.removeWithoutCache(foundTransaction);
        transactionDao.removeWithoutCache(foundTransaction.getName());
        foundTransaction = transactionDao.findByName(TRANSACTION_NAME);
        assertThat(foundTransaction).isNotNull();
    }
}
