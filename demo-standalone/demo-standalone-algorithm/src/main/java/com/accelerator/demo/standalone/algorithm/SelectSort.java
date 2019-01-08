package com.accelerator.demo.standalone.algorithm;

import java.util.Arrays;

public class SelectSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
            int k = i;
            for (int j = k + 1; j < arr.length; j++) {// 选最小的记录
                if (arr[j] < arr[k]) {
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            int temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 2, 3, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
