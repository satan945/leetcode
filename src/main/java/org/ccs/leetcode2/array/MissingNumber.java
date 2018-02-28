/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

/**
 * 268. Missing Number
 * 
 * <p>
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * 
 * </p>
 * 
 * @author abel created on 2018/2/25 下午5:06
 * @version $Id$
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int i = 0;
        while (i < len) {
            int target = nums[i];
            if (nums[i] == i || nums[i] < 0) {
                i++;
            } else if (nums[i] == len) {
                nums[i] = -1;
            } else if (nums[target] != target) {
                swap(nums, i, target);
            } else {
                i++;
            }
        }
        for (i = 0; i < len; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return len;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
        MissingNumber solution = new MissingNumber();
        System.out.println(solution.missingNumber(nums));
    }
}
