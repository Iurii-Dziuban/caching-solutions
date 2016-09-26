package org.caching.lombok;

import lombok.val;
import org.caching.data.lombok.BuilderAndGetterExample;
import org.caching.data.lombok.LazyGetterSneakyThrowsExample;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
public class LazyGetterSneakyThrowsExampleTest {
    @Test
    public void test(){
        val example = new LazyGetterSneakyThrowsExample();
        long startTime = System.currentTimeMillis();
        String cached = example.getCached();
        long endTime = System.currentTimeMillis();

        Assert.assertTrue(endTime - startTime > 10000);
        Assert.assertEquals("cached", cached);

        // check cached

        startTime = System.currentTimeMillis();
        cached = example.getCached();
        endTime = System.currentTimeMillis();

        Assert.assertTrue(endTime - startTime < 10000);
        Assert.assertEquals("cached", cached);
    }
}
