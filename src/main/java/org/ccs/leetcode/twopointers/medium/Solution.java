/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.twopointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author abel created on 2017/8/10 下午10:23
 * @version $Id$
 */
public class Solution {

    /**
     * 15. 3Sum
     * <p>
     * https://leetcode.com/problems/3sum
     * <p>
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets
     * in the array which gives the sum of zero.
     *
     * Note: The solution set must not contain duplicate triplets.
     *
     * For example, given array S = [-1, 0, 1, 2, -1, -4],
     *
     * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
     * </p>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i + 2 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            int target = -nums[i];
            while (k > j) {
                if (nums[j] + nums[k] == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    /**
     * 16. 3Sum Closest
     * <p>
     * https://leetcode.com/problems/3sum-closest
     * <p>
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
     * Return the sum of the three integers. You may assume that each input would have exactly one solution.
     *
     * For example, given array S = {-1 2 1 -4}, and target = 1.
     *
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     * </p>
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i + 2 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int remain = target - nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == remain) {
                    return target;
                } else {
                    int curDiff = Math.abs(remain - (nums[j] + nums[k]));
                    if (curDiff < diff) {
                        diff = curDiff;
                        result = nums[i] + nums[j] + nums[k];
                    }
                    if (nums[j] + nums[k] > remain) {
                        k--;
                        while (j < k && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    } else {
                        j++;
                        while (j < k && nums[j] == nums[j - 1]) {
                            j++;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     *
     * @param num
     * @param target
     * @return
     */
    public int threeSumClosestSimple(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum == target) {
                    return target;
                }
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }

    /**
     * 
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {

        return 0;
    }
}
