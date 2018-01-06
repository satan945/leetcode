/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 473. Matchsticks to Square
 * <p>
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please
 * find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can
 * link them up, and each matchstick must be used exactly one time.
 * 
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be
 * true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 * </p>
 * 
 * @author Abel created on 2018/1/5 15:50
 * @version $Id$
 */
public class MatchsticksToSquare {
    private int count = 0;

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(nums);
        int len = sum / 4;
        if (nums[nums.length - 1] > len) {
            return false;
        }
        return dfsBuildSquare(nums, new int[4], nums.length - 1, len);
    }

    private boolean dfsBuildSquare(int[] nums, int[] sums, int pos, int len) {
        if (pos < 0) {
            return sums[0] == len && sums[1] == len && sums[2] == len && sums[3] == len;
        }
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] + nums[pos] > len) {
                continue;
            }
            sums[i] += nums[pos];
            if (dfsBuildSquare(nums, sums, pos - 1, len)) {
                return true;
            }
            sums[i] -= nums[pos];
        }
        return false;
    }

    public static void main(String[] args) {
        MatchsticksToSquare solution = new MatchsticksToSquare();
        System.out.println(solution.makesquare(new int[] { 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3 }));
    }
}
