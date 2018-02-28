/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.fb;

/**
 * 209. Minimum Size Subarray Sum
 * 
 * https://leetcode.com/problems/minimum-size-subarray-sum
 * 
 * @author abel created on 2018/2/28 上午10:42
 * @version $Id$
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {
        int res = nums.length;
        int sum = 0;
        int i = 0, j = i + 1;
        while (j < nums.length) {
            while (sum < s) {
                sum += nums[j++];
            }
            res = Math.min(res, j - i);
            while (sum >= s) {
                sum -= nums[i++];
            }
        }
        return res;

    }
}
