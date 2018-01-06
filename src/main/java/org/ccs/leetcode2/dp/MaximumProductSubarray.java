/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

/**
 * 152. Maximum Product Subarray
 * <p>
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * 
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 * </p>
 * 
 * @author Abel created on 2018/1/5 15:38
 * @version $Id$
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxPro = nums[0];
        int maxPre = nums[0];
        int minPre = nums[0];
        int maxCur;
        int minCur;
        for (int i = 1; i < nums.length; i++) {
            maxCur = Math.max(Math.max(maxPre * nums[i], minPre * nums[i]), nums[i]);
            minCur = Math.min(Math.min(maxPre * nums[i], minPre * nums[i]), nums[i]);
            maxPro = Math.max(maxPro, maxCur);
            maxPre = maxCur;
            minPre = minCur;
        }
        return maxPro;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { -2, 3, 2, 4 };
        MaximumProductSubarray solution = new MaximumProductSubarray();
        System.out.println(solution.maxProduct(nums));
    }
}
