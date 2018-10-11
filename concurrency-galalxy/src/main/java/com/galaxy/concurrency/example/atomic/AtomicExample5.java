package com.galaxy.concurrency.example.atomic;

import com.galaxy.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
@ThreadSafe
public class AtomicExample5 {
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    public volatile int count = 100;

    //private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1:{}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2:{}", example5.getCount());
        } else {
            log.info("update fail:{}", example5.getCount());
        }
    }
}
