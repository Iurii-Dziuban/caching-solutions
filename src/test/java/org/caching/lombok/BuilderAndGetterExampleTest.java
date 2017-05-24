package org.caching.lombok;

import org.caching.data.lombok.BuilderAndGetterExample;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
public class BuilderAndGetterExampleTest {

    @Test
    public void test(){
        BuilderAndGetterExample.BuilderAndGetterExampleBuilder builder = BuilderAndGetterExample.builder();
        BuilderAndGetterExample iurii = builder.name("Iurii").age(26).occupation("Java Developer").occupation("Traveler").build();
        assertThat(iurii.getName()).isEqualTo("Iurii");
        assertThat(iurii.getAge()).isEqualTo(26);
        assertThat(iurii.getOccupations()).hasSize(2);
        assertThat(iurii.getOccupations()).contains("Java Developer");
        assertThat(iurii.getOccupations()).contains("Traveler");
    }
}
