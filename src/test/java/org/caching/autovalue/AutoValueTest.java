package org.caching.autovalue;

import org.caching.data.autovalue.Transaction;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by iurii.dziuban on 27.09.2016.
 */
public class AutoValueTest {

    @Test
    public void testAutoValue() {
        Transaction transaction = Transaction.builder().setName("Iurii").setId(4).build();
        Assert.assertEquals("Iurii", transaction.name());
        Assert.assertEquals(4, transaction.id());
        Assert.assertEquals("Transaction{id=4, name=Iurii}", transaction.toString());
    }
}
