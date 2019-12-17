package com.galaxy.bigdata.hadoop.hdfs;

import org.apache.spark.sql.execution.columnar.INT;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pengwang
 * @date 2019/12/04
 */
public class Test2 {

    // 保存反射获取的Test1中list字段的值
    protected static List<Integer> list2;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        Test1 test1 = Test1.newInstance(list1);
        // 1、反射获取list对象
        Field field = test1.getClass().getDeclaredField("list");
        // 2、设置访问字段时跳过安全检查，否则会抛出java.lang.IllegalAccessException异常
        field.setAccessible(true);
        // 3、反射获取Test1中list字段的值
        list2 = (List) field.get(test1);
        // 4、比较list1和list2是否指向同一地址，输出结果true
        System.out.println(list2 == list1);

        // 5、打印输出list的值，观察变化
        while (true) {
            System.out.println(list2);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}