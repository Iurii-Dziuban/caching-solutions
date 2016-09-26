package org.caching.data.lombok;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by iurii.dziuban on 26.09.2016.
 */
@Slf4j(topic = "SynchronizedAndLogExample")
public class SynchronizedAndLogExample {

    private final Object readLock = new Object();

    @Synchronized("readLock")
    public String print() {
        LOGGER.info("readLock");
        return "readLock";
    }
}
