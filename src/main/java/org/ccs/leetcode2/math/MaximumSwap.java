/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.math;

import java.util.Arrays;

/**
 * 670. Maximum Swap
 * <p>
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the
 * maximum valued number you could get.
 * </p>
 * 
 * @author abel created on 2018/2/1 下午4:05
 * @version $Id$
 */
public class MaximumSwap {

    public int maximumSwap(int num) {
        char[] numArray = String.valueOf(num).toCharArray();
        if (numArray.length < 2) {
            return num;
        }
        int firstPos = -1;
        for (int i = 0; i < numArray.length - 1; i++) {
            int num1 = numArray[i] - '0';
            int num2 = numArray[i + 1] - '0';
            if (num1 < num2) {
                firstPos = i;
                break;
            }
            if (num1 <= num2 && firstPos == -1) {
                firstPos = i;
            }
        }
        if (firstPos == -1) {
            return num;
        }
        int max = -1;
        int secondPos = -1;
        for (int j = numArray.length - 1; j > firstPos; j--) {
            int numJ = numArray[j] - '0';
            if (numJ > max) {
                max = numJ;
                secondPos = j;
            }
        }
        int changePos = -1;
        for (int i = 0; i <= firstPos; i++) {
            if (numArray[i] - '0' < max) {
                changePos = i;
                break;
            }
        }
        if (changePos >= 0) {
            swap(numArray, changePos, secondPos);
        }
        return Integer.parseInt(String.valueOf(numArray));
    }

    private void swap(char[] array, int firstPos, int secondPos) {
        char tmp = array[firstPos];
        array[firstPos] = array[secondPos];
        array[secondPos] = tmp;
    }

    public int maximumSwapUsingBucket(int num) {
        char[] array = String.valueOf(num).toCharArray();
        int[] bucket = new int[10];
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] - '0'] = i;
        }
        for (int i = 0; i < array.length; i++) {
            for (int n = 9; n > array[i] - '0'; n--) {
                if (bucket[n] > i) {
                    char tmp = array[i];
                    array[i] = array[bucket[n]];
                    array[bucket[n]] = tmp;
                    return Integer.parseInt(String.valueOf(array));
                }

            }
        }
        return num;

    }

    public static void main(String[] args) {
        MaximumSwap solution = new MaximumSwap();
        System.out.println(solution.maximumSwap(2736));
        System.out.println(solution.maximumSwap(9973));
        System.out.println(solution.maximumSwap(115));
        System.out.println(solution.maximumSwap(1993));
        System.out.println(solution.maximumSwap(10909091));
        System.out.println(solution.maximumSwap(21909091));
        System.out.println(solution.maximumSwap(99901));
    }
}
