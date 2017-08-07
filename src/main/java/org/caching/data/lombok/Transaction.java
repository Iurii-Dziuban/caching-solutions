package org.caching.data.lombok;

/**
 * Created by iurii.dziuban on 13.07.2016.
 */
import lombok.Data;

import java.io.Serializable;

/**
 * Base simple model class with two fields id and name
 */
@Data
public class Transaction implements Serializable {
    private Integer id;
    private String name;
}
