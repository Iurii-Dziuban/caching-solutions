package org.caching.data.lombok;

import lombok.*;

import java.util.Set;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
@Builder
@Getter(AccessLevel.PUBLIC)
@ToString(exclude = "occupations")
@EqualsAndHashCode(of = {"name", "age"})
public class BuilderAndGetterExample {
    private String name;
    private int age;
    @Singular private Set<String> occupations;
}
