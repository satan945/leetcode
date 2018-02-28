/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k
 * 
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k
 * 
 * @author abel created on 2018/2/28 上午10:40
 * @version $Id$
 */
public class MaximumSizeSubarraySumEqualsk {

    public int maxSubArrayLen(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                res = i + 1;
            } else if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }
        }
        return res;
    }

}
