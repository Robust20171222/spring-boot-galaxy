package com.galaxy.concurrency.algorithm;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wangpeng
 * Date: 2018/10/21
 * Time: 10:53
 */
public class CollectionTest {

    private static int SIZE = 111111;

    @Test
    public void testSynchronizeList() {
        List<String> list = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(list);
        synchronizedList.add("aaa");
        synchronizedList.add("bbb");
        for (int i = 0; i < synchronizedList.size(); i++) {
            System.out.println(synchronizedList.get(i));
        }
    }

    @Test
    public void testLoopList() {
        List<Integer> arrayList = new ArrayList<Integer>(SIZE);
        List<Integer> linkedList = new LinkedList<Integer>();

        for (int i = 0; i < SIZE; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        loopList(arrayList);
        loopList(linkedList);
        System.out.println();
    }

    @Test
    public void testLoopLinkedHashMap() {
        LinkedHashMap<String, String> linkedHashMap =
                new LinkedHashMap<String, String>(16, 0.75f, true);
        linkedHashMap.put("111", "111");
        linkedHashMap.put("222", "222");
        linkedHashMap.put("333", "333");
        linkedHashMap.put("444", "444");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.get("111");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.put("222", "2222");
        loopLinkedHashMap(linkedHashMap);
    }

    @Test
    public void testLoopList2() {
//        List<Integer> list = new ArrayList<Integer>();

//        List<Integer> list = new LinkedList<>();

        List<Integer> list = new CopyOnWriteArrayList<Integer>();

        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        T1 t1 = new T1(list);
        T2 t2 = new T2(list);
        t1.start();
        t2.start();
    }

    public static void loopLinkedHashMap(LinkedHashMap<String, String> linkedHashMap)
    {
        Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();

        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }

    private static void loopList(List<Integer> list) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        System.out.println(list.getClass().getSimpleName() + "使用普通for循环遍历时间为" +
                (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (Integer i : list) {

        }
        System.out.println(list.getClass().getSimpleName() + "使用foreach循环遍历时间为" +
                (System.currentTimeMillis() - startTime) + "ms");
    }

    public static class T1 extends Thread {
        private List<Integer> list;

        public T1(List<Integer> list) {
            this.list = list;
        }

        public void run() {
            for (Integer i : list) {
            }
        }
    }

    public static class T2 extends Thread {
        private List<Integer> list;

        public T2(List<Integer> list) {
            this.list = list;
        }

        public void run() {
            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
            }
        }
    }
}
