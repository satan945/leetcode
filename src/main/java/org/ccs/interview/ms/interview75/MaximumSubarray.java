/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

/**
 * 53. Maximum Subarray
 * 
 * <p>
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * 
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * </p>
 * 
 * @author abel created on 2018/2/22 下午4:52
 * @version $Id$
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        int maxSum = sum[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum[i - 1] > 0) {
                sum[i] = sum[i - 1] + nums[i];
            } else {
                sum[i] = nums[i];
            }
            maxSum = Math.max(maxSum, sum[i]);
        }
        return maxSum;
    }
}
