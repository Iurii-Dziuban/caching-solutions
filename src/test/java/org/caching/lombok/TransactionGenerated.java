package org.caching.lombok;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by iurii.dziuban on 04.10.2016.
 */
public class TransactionGenerated implements Serializable{
    private Integer id;
    private String name;

    public TransactionGenerated() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        return Objects.deepEquals(this, o);
    }

    public int hashCode() {
        return Objects.hashCode(this);
    }

    public String toString() {
        return "TransactionGenerated(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
