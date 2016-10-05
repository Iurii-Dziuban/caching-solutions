package org.caching.data.value.generated;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by iurii.dziuban on 04.10.2016.
 */
public class Transaction implements Serializable{
    private Integer id;
    private String name;

    public Transaction() {
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
        return "Transaction(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
