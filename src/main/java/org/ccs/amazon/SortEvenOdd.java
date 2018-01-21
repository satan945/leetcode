/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author abel created on 2018/1/17 下午12:23
 * @version $Id$
 */
public class SortEvenOdd {
    public void sortByEven(int[] nums) {
        Integer[] integers = transferToIntegerArray(nums);
        Arrays.sort(integers, (o1, o2) -> {
            if ((o1 % 2 == 0 && o2 % 2 == 0) || (o1 % 2 == 1 && o2 % 2 == 1)) {
                return o1 - o2;
            } else {
                if (o1 % 2 == 1) {
                    return -1;
                }
                if (o2 % 2 == 1) {
                    return 1;
                }
            }
            return 0;
        });
        for (int i = 0; i < nums.length; i++) {
            nums[i] = integers[i];
        }
        System.out.println("1");
    }

    private Integer[] transferToIntegerArray(int[] nums) {
        Integer[] res = new Integer[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = nums[i];
        }
        return res;
    }

    public static void main(String[] args) {


    }
}
