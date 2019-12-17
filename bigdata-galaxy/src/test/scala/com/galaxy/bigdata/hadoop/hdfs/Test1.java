package com.galaxy.bigdata.hadoop.hdfs;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pengwang
 * @date 2019/12/04
 */
public class Test1 {

    // 保存随机数字
    private final List<Integer> list;

    static Test1 newInstance(List<Integer> list) {
        return new Test1(list);
    }

    // 类初始化时调用启动add和remove方法
    private Test1(List<Integer> list) {
        add();
        remove();
        this.list = list;
    }

    public List<Integer> getList() {
        return list;
    }

    /**
     * 定时添加随机数字
     */
    public final void add() {
        ScheduledThreadPoolExecutor executors = new ScheduledThreadPoolExecutor(1);
        executors.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                list.add(new Random().nextInt(100));
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    /**
     * 定时移除随机数字
     */
    public final void remove() {
        ScheduledThreadPoolExecutor executors = new ScheduledThreadPoolExecutor(21);
        executors.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                list.remove(0);
            }
        }, 1, 2, TimeUnit.SECONDS);
    }
}