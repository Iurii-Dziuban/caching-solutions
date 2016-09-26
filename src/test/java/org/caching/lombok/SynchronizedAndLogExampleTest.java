package org.caching.lombok;

import org.caching.data.lombok.SynchronizedAndLogExample;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
public class SynchronizedAndLogExampleTest {

    @Test
    public void test() {
        SynchronizedAndLogExample synchronizedExample = new SynchronizedAndLogExample();
        assertEquals("readLock", synchronizedExample.print());
    }
}
