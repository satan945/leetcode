/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.dp.easy;

/**
 * 303. Range Sum Query - Immutable
 * <p>
 * https://leetcode.com/problems/range-sum-query-immutable
 * <p>
 * 
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * 
 * Example:
 * 
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * 
 * sumRange(0, 2) -> 1
 * 
 * sumRange(2, 5) -> -1
 * 
 * sumRange(0, 5) -> -3
 * 
 * Note: You may assume that the array does not change. There are many calls to sumRange function.
 * </p>
 * 
 * @author Abel created on 2017/9/1 11:36
 * @version $Id$
 */
public class NumArray {
    private int[] sums = null;

    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (sums == null || sums.length == 0) {
            return 0;
        }
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }
}
