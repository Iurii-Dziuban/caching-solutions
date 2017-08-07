package org.caching.lombok;

import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(endTime - startTime > 5000);
        assertThat(cached).isEqualTo("cached");

        // check cached

        startTime = System.currentTimeMillis();
        cached = example.getCached();
        endTime = System.currentTimeMillis();

        assertThat(endTime - startTime < 5000).isTrue();
        assertThat(cached).isEqualTo("cached");
    }
}
