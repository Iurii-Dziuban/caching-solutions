package org.caching;

import com.google.common.base.Suppliers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://dzone.com/articles/lazy-loading-and-caching-objects-in-java-with-guava
 * Created by iurii.dziuban on 20/09/2017.
 */
public class GuavaCacheTest {

    public static final int TIMEOUT = 2000;

    @Test
    public void test() throws InterruptedException {
        Supplier memoSavedWithExpiration = Suppliers.memoizeWithExpiration(
                GuavaCacheTest::helloWorldSupplier, 2100, TimeUnit.MILLISECONDS);
        long begin = System.currentTimeMillis();
        memoSavedWithExpiration.get();
        assertThat(System.currentTimeMillis() - begin).isGreaterThanOrEqualTo(TIMEOUT);
        begin = System.currentTimeMillis();
        memoSavedWithExpiration.get();
        assertThat(System.currentTimeMillis() - begin).isLessThan(TIMEOUT);
        TimeUnit.MILLISECONDS.sleep(100);
        begin = System.currentTimeMillis();
        memoSavedWithExpiration.get();
        assertThat(System.currentTimeMillis() - begin).isGreaterThanOrEqualTo(TIMEOUT);
        begin = System.currentTimeMillis();
        memoSavedWithExpiration.get();
        assertThat(System.currentTimeMillis() - begin).isLessThan(TIMEOUT);

    }

    private static String helloWorldSupplier() {
        try {
            TimeUnit.MILLISECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Method is called");
        return "hello world";
    }
}
