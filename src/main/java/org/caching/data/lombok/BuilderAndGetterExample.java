package org.caching.data.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
@Builder
@Getter
public class BuilderAndGetterExample {
    private String name;
    private int age;
    @Singular private Set<String> occupations;
}
