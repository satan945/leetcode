/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.lintcode.contest.mock4;

/**
 * 604. Window Sum
 *
 * http://www.lintcode.com/en/problem/window-sum/
 *
 * @author Abel created on 2018/2/12 22:06
 * @version $Id$
 */
public class WindowSum {
    public int[] winSum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                sum += nums[i];
            } else if (i == k - 1) {
                sum += nums[i];
                res[0] = sum;
            } else {
                sum -= nums[i - k];
                sum += nums[i];
                res[i - k + 1] = sum;
            }
        }
        return res;
        // write your code here
    }

    public static void main(String[] args) {
        WindowSum solution = new WindowSum();
        int[] res = solution.winSum(new int[] { 1, 2, 7, 8, 5 }, 3);
        System.out.println(res);
    }
}
