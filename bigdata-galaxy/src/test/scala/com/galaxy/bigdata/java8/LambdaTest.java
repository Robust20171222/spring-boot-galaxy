package com.galaxy.bigdata.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author pengwang
 * @date 2019/07/24
 */
public class LambdaTest {

    @Test
    public void test1() {
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();
    }
}