package com.galaxy.concurrency.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @link https://www.baeldung.com/java-gc-overhead-limit-exceeded
 */
public class OutOfMemoryGCLimitExceed {

    public static void addRandomDataToMap() {
        Map<Integer, String> dataMap = new HashMap<>();
        Random r = new Random();
        while (true) {
            dataMap.put(r.nextInt(), String.valueOf(r.nextInt()));
        }
    }

    public static void main(String[] args) {
        addRandomDataToMap();
    }
}
