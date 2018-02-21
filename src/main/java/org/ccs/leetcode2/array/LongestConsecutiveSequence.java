/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 128. Longest Consecutive Sequence
 * <p>
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements sequence is [1, 2, 3, 4]. Return its
 * length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 * </p>
 * 
 * @author abel created on 2018/2/5 下午6:15
 * @version $Id$
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;

        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int sum = left + right + 1;
                res = Math.max(res, sum);
                map.put(num - left, sum);
                map.put(num + right, sum);
            }
        }
        return res;

    }
}
