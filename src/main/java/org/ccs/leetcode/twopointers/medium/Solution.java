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
        if (nums.length < 3) {
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            count += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return count;
    }

    public int twoSumSmaller(int[] nums, int start, int target) {
        int count = 0;
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    /**
     * <p>
     * https://leetcode.com/problems/max-consecutive-ones-ii
     * <p>
     * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
     * 
     * Example 1:
     *
     * Input: [1,0,1,1,0]
     *
     * Output: 4
     *
     * Explanation: Flip the first zero will get the the maximum number of consecutive 1s. After flipping, the maximum
     * number of consecutive 1s is 4.
     *
     * Note:
     * 
     * The input array will only contain 0 and 1. The length of input array is a positive integer and will not exceed
     * 10,000 Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't
     * store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     *
     *
     * Follow up:
     * 
     * What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers
     * coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     * </p>
     * 
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        return 0;
    }
}
