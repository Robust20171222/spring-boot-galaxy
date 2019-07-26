package com.galaxy.gradle.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author pengwang
 * @date 2019/07/26
 */
public class DishTest {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void test1() {
        List names1 = menu.stream().filter(dish -> dish.getCalories() > 300).map(dish -> dish.getName()).limit(3).collect(toList());
        System.out.println("names1--------" + names1);
        List names2 = menu.stream().filter(dish -> {
            System.out.println("filtering" + dish.getName());
            return dish.getCalories() > 300;
        }).map(dish -> {
            System.out.println("mapping" + dish.getName());
            return dish.getName();
        }).limit(3).collect(toList());
        System.out.println("names2--------" + names2);
    }

    /**
     * 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
     * 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
     * 表数对
     */
    @Test
    public void test2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());
        pairs.stream().forEach(i -> System.out.println(Arrays.toString(i)));
    }

    /**
     * 怎样用map和reduce方法数一数流中有多少个菜呢？
     * 要解决这个问题，你可以把流中每个元素都映射成数字1，然后用reduce求和。这
     * 相当于按顺序数流中的元素个数
     */
    @Test
    public void test3() {
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
        System.out.println(count);
    }
}