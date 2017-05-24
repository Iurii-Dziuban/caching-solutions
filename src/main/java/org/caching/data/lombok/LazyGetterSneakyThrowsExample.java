package org.caching.data.lombok;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
@Slf4j
public class LazyGetterSneakyThrowsExample {

    @Getter(lazy=true)
    private final String cached = expensive();

    @SneakyThrows(InterruptedException.class)
    private String expensive() {
        LOGGER.info("Expensive operation");
        Thread.sleep(10000);
        return "cached";
    }

}
