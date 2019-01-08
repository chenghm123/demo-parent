package com.accelerator.demo.standalone.algorithm;

//评测题目: java实现一个快速排序算法

import java.util.Arrays;

public class FastSort {

    public static int partition(int[] arr, int l, int h) {
        int key = arr[l];
        while (l < h) {
            while (arr[h] >= key && h > l) {
                h--;
            }
            arr[l] = arr[h];
            while (arr[l] <= key && h > l) {
                l++;
            }
            arr[h] = arr[l];
        }
        arr[h] = key;
        return h;
    }

    public static void sort(int arr[], int l, int h) {
        if (l >= h) {
            return;
        }
        int i = partition(arr, l, h);
        sort(arr, l, i - 1);
        sort(arr, i + 1, h);
    }

    /**
     * 快速排序理解要稍微复杂一些、可以参考
     * http://blog.csdn.net/vayne_xiao/article/details/53508973
     */
    public static void main(String[] args) {
        int[] arr = new int[]{9, 2, 3, 4, 5};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}