package org.caching.data.value;

/**
 * Created by iurii.dziuban on 13.07.2016.
 */
import lombok.Data;

/**
 * Base simple model class with two fields id and name
 */
@Data
public class Transaction {
    private Integer id;
    private String name;
}
