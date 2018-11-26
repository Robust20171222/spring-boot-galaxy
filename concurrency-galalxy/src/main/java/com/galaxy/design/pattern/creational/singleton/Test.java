package com.galaxy.design.pattern.creational.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 10:07
 */
public class Test {

    /**
     * 懒汉式单例测试
     */
    @org.junit.Test
    public void testLazySingleton() throws InterruptedException {
        Thread t1 = new Thread(new T());
        Thread t2 = new Thread(new T());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Program end");
    }

    /**
     * 单例序列化测试
     *
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @org.junit.Test
    public void testHungrySingletonWithSerializable() throws ClassNotFoundException, IOException {
        // HungrySingleton instance = HungrySingleton.getInstance();
        EnumInstance instance = EnumInstance.getInstance();
        instance.setData(new Object());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("singleton_file"));
        objectOutputStream.writeObject(instance);

        File file = new File("singleton_file");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        //HungrySingleton newInstance = (HungrySingleton) objectInputStream.readObject();
        EnumInstance newInstance = (EnumInstance) objectInputStream.readObject();

//        System.out.println(instance);
//        System.out.println(newInstance);
        System.out.println(instance.getData());
        System.out.println(newInstance.getData());
//        System.out.println(instance == newInstance);
        System.out.println(instance.getData() == newInstance.getData());
    }

    /**
     * 单例反射测试
     *
     * @throws NoSuchMethodException
     */
    @org.junit.Test
    public void testHungrySingletonWithReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //Class objectClass = HungrySingleton.class;
        // Class objectClass = StaticInnerClassSingleton.class;
        //Class objectClass = LazySingleton.class;

        Class objectClass = EnumInstance.class;
        Constructor constructor = objectClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);

//        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
//        HungrySingleton object = (HungrySingleton) constructor.newInstance();
//        System.out.println(hungrySingleton);
//        System.out.println(object);
//        System.out.println(hungrySingleton == object);

//        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
//        StaticInnerClassSingleton newInstance = (StaticInnerClassSingleton)constructor.newInstance();

//        LazySingleton newInstance = (LazySingleton) constructor.newInstance();
//        LazySingleton instance = LazySingleton.getInstance();

        EnumInstance newInstance = (EnumInstance) constructor.newInstance("Hello", 0);
        EnumInstance instance = EnumInstance.getInstance();

        System.out.println(instance);
        System.out.println(newInstance);
        System.out.println(instance == newInstance);
    }
}
