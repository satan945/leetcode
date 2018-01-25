/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.sum;

import java.util.Arrays;

/**
 * 16. 3Sum Closest
 * <p>
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * </p>
 * 
 * @author Abel created on 2018/1/24 16:20
 * @version $Id$
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sub = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1, high = nums.length - 1, t = target - nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == t) {
                    return target;
                }
                int sum = nums[low] + nums[high];
                int curSub = Math.abs(t - sum);
                if (curSub < sub) {
                    res = nums[i] + sum;
                    sub = curSub;
                }
                if (sum < t) {
                    low++;
                } else if (sum > t) {
                    high--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        System.out.println(threeSumClosest.threeSumClosest(new int[]{-1 ,2 ,1 ,-4},1));
        System.out.println(threeSumClosest.threeSumClosest(new int[]{0 ,1 ,2},0));
        System.out.println(threeSumClosest.threeSumClosest(new int[]{0 ,2 ,1,-3},1));
    }
}
