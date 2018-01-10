/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k
 * 
 * @author abel created on 2018/1/9 下午6:55
 * @version $Id$
 */
public class MaximumSizeSubarraySumEqualsK {

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                len = i + 1;
            } else if (map.containsKey(sum - k)) {
                len = Math.max(len, i - map.get(sum - k));
            }
            map.putIfAbsent(sum, i);
        }
        return len;
    }

}
