package org.caching.data.autovalue;

import com.google.auto.value.AutoValue;

/**
 * Created by iurii.dziuban on 27.09.2016.
 */
@AutoValue
public abstract class Transaction {
    private Integer id;
    private String name;

    public abstract int id();
    public abstract String name();

    public static Builder builder() {
        return new AutoValue_Transaction.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setName(String name);
        public abstract Builder setId(int id);
        public abstract Transaction build();
    }
}
