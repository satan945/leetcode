/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.sum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * </p>
 * 
 * @author Abel created on 2018/1/24 15:56
 * @version $Id$
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { map.get(target - nums[i]), i };
            }
            map.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }
}
