package org.caching.lombok;

import org.caching.data.lombok.BuilderAndGetterExample;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
public class BuilderAndGetterExampleTest {

    @Test
    public void test(){
        BuilderAndGetterExample.BuilderAndGetterExampleBuilder builder = BuilderAndGetterExample.builder();
        BuilderAndGetterExample iurii = builder.name("Iurii").age(26).occupation("Java Developer").occupation("Traveler").build();
        Assert.assertEquals("iurii",iurii.getName());
        Assert.assertEquals(26,iurii.getAge());
        Assert.assertEquals(2,iurii.getOccupations().size());
        Assert.assertTrue(iurii.getOccupations().contains("Java Developer"));
        Assert.assertTrue(iurii.getOccupations().contains("Traveler"));
    }
}
