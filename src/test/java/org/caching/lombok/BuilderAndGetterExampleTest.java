package org.caching.lombok;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
public class BuilderAndGetterExampleTest {

    @Test
    public void test(){
        BuilderAndGetterExample.BuilderAndGetterExampleBuilder builder = BuilderAndGetterExample.builder();
        BuilderAndGetterExample person = builder.name("Iurii").age(26).occupation("Java Developer").occupation("Traveler").build();
        assertThat(person.getName()).isEqualTo("Iurii");
        assertThat(person.getAge()).isEqualTo(26);
        assertThat(person.getOccupations()).hasSize(2);
        assertThat(person.getOccupations()).contains("Java Developer");
        assertThat(person.getOccupations()).contains("Traveler");
    }
}
