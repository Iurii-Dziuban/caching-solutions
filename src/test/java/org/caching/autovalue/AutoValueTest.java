package org.caching.autovalue;

import org.caching.data.autovalue.Transaction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by iurii.dziuban on 27.09.2016.
 */
public class AutoValueTest {

    @Test
    public void testAutoValue() {
        Transaction transaction = Transaction.builder().setName("Iurii").setId(4).build();
        assertThat(transaction.name()).isEqualTo("Iurii");
        assertThat(transaction.id()).isEqualTo(4);
        assertThat(transaction.toString()).isEqualTo("Transaction{id=4, name=Iurii}");
    }
}
