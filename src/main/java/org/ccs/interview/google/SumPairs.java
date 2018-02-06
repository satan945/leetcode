/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * given an integer array, return a list of integer pairs whose sum quals to zero
 * </p>
 * 
 * @author abel created on 2018/2/1 下午3:59
 * @version $Id$
 */
public class SumPairs {

    List<int[]> getPairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        List<int[]> res = new ArrayList<>();
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == 0) {
                res.add(new int[] { nums[i], nums[j] });
                i++;
                j--;
            } else if (sum > 0) {
                j--;
            } else {
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SumPairs solution = new SumPairs();
        List<int[]> res = solution.getPairs(new int[]{1,2,3,4,-1,-2,0,0});
        System.out.println(res);
    }
}
