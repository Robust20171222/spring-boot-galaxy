package com.galaxy.concurrency.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangpeng
 * Date: 2018/10/24
 * Time: 21:16
 */
public class CollectionTest {

    public static Map map = new HashMap<String, Object>();

    public static Map map3 = new ConcurrentHashMap();

    public static void main(String[] args) {

        CollectionTest collectionTest = new CollectionTest();

        Map map2 = Collections.synchronizedMap(map);

    }
}
