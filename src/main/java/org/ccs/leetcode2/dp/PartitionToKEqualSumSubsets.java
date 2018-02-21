/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

import java.util.Arrays;

/**
 * 698. Partition to K Equal Sum Subsets
 * <p>
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k
 * non-empty subsets whose sums are all equal.
 * </p>
 * 
 * @author abel created on 2018/2/7 下午1:18
 * @version $Id$
 */
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return false;
        }
        int sum = calSum(nums);
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        boolean[] visited = new boolean[nums.length];
        return false;
    }

    private int calSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
