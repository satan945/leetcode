/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * <p>
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
 * equals to k.
 *
 * Note:
 *
 * 1.The length of the array is in range [1, 20,000].
 * 
 * 2.The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * </p>
 * 
 * @author abel created on 2018/1/21 下午12:02
 * @version $Id$
 */
public class SubarraySumEqualsK {

    // Using Cummulative sum [Accepted]
    public int subarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySumWithoutSpace(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = nums[0];
            for (int end = start + 1; end < nums.length; end++) {
                sum = sum + nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySumWithoutMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0, sum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum - k, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();
        int[] nums = new int[] { 1, 1, 1 };
        System.out.println(solution.subarraySum(nums, 2));
    }
}
