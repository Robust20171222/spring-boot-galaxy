package com.galaxy.concurrency.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wangpeng
 * Date: 2018/10/27
 * Time: 16:26
 *
 * @see https://juejin.im/post/5ab9ae9cf265da23830ae617
 */
public class SortTest {

    /**
     * 冒泡排序
     */
    @Test
    public void bubbleSort() {
        int[] arrays = new int[]{1, 3, 2, 2, 34, 55};

        int isChange = 0;
        //外层循环是排序的趟数
        for (int i = 0; i < arrays.length - 1; i++) {

            //每比较一趟就重新初始化为0
            isChange = 0;

            //内层循环是当前趟数需要比较的次数
            for (int j = 0; j < arrays.length - i - 1; j++) {
                //前一位与后一位与前一位比较，如果前一位比后一位要大，那么交换
                if (arrays[j] > arrays[j + 1]) {
                    int temp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = temp;

                    //如果进到这里面了，说明发生置换了
                    isChange = 1;

                }
            }
            //如果比较完一趟没有发生置换，那么说明已经排好序了，不需要再执行下去了
            if (isChange == 0) {
                break;
            }
        }

        System.out.println("公众号Java3y" + Arrays.toString(arrays));
    }

    /**
     * 选择排序
     */
    @Test
    public void selectTest() {
        int[] arrays = new int[]{1, 3, 2, 2, 9, 6, 4, 7, 8, 22, 13, 34, 55};

        int pos = 0;
        int temp = 0;
        //外层循环控制需要排序的趟数
        for (int i = 0; i < arrays.length - 1; i++) {
            //新的趟数、将角标重新赋值为0
            pos = 0;
            //内层循环控制遍历数组的个数并得到最大数的角标
            for (int j = 0; j < arrays.length - i; j++) {

                if (arrays[j] > arrays[pos]) {
                    pos = j;
                }

            }
            //交换
            temp = arrays[pos];
            arrays[pos] = arrays[arrays.length - 1 - i];
            arrays[arrays.length - 1 - i] = temp;
        }
        System.out.println("公众号Java3y" + Arrays.toString(arrays));
    }

    /**
     * 插入排序
     */
    @Test
    public void insertSort() {
        int[] arrays = new int[]{1, 3, 2, 2, 9, 6, 4, 7, 8, 22, 13, 34, 55};

        //临时变量
        int temp;


        //外层循环控制需要排序的趟数(从1开始因为将第0位看成了有序数据)
        for (int i = 1; i < arrays.length; i++) {

            temp = arrays[i];

            //如果前一位(已排序的数据)比当前数据要大，那么就进入循环比较[参考第二趟排序]

            int j = i - 1;

            while (j >= 0 && arrays[j] > temp) {

                //往后退一个位置，让当前数据与之前前位进行比较
                arrays[j + 1] = arrays[j];

                //不断往前，直到退出循环
                j--;

            }
            //退出了循环说明找到了合适的位置了，将当前数据插入合适的位置中
            arrays[j + 1] = temp;

        }
        System.out.println("公众号Java3y" + Arrays.toString(arrays));
    }

    /**
     * 快速排序测试
     */
    @Test
    public void quickSort() {
        int[] arrays = new int[]{1, 3, 2, 2, 9, 6, 4, 7, 8, 22, 13, 34, 55};
        quickSort(arrays, 0, arrays.length - 1);
        System.out.println("公众号Java3y" + Arrays.toString(arrays));
    }


    /**
     * 快速排序
     *
     * @param arr
     * @param L   指向数组第一个元素
     * @param R   指向数组最后一个元素
     */
    public static void quickSort(int[] arr, int L, int R) {
        int i = L;
        int j = R;

        //支点
        int pivot = arr[(L + R) / 2];

        //左右两端进行扫描，只要两端还没有交替，就一直扫描
        while (i <= j) {

            //寻找直到比支点大的数
            while (pivot > arr[i])
                i++;

            //寻找直到比支点小的数
            while (pivot < arr[j])
                j--;

            //此时已经分别找到了比支点小的数(右边)、比支点大的数(左边)，它们进行交换
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        //上面一个while保证了第一趟排序支点的左边比支点小，支点的右边比支点大了。


        //“左边”再做排序，直到左边剩下一个数(递归出口)
        if (L < j)
            quickSort(arr, L, j);

        //“右边”再做排序，直到右边剩下一个数(递归出口)
        if (i < R)
            quickSort(arr, i, R);
    }

}
