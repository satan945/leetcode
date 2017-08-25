/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algrithm.sort;

/**
 * @author abel created on 2017/8/25 下午3:58
 * @version $Id$
 */
public class BubbleSort {
    public void sort(int nums[]) {
        if (nums.length <= 1) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    Common.swapArray(nums, i, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 4, 2, 5, 0, 3, -1, 0, 3, 4, 99, 9, 88 };
        new BubbleSort().sort(nums);
        System.out.println(nums);
    }
}
