/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. Missing Ranges
 * 
 * https://leetcode.com/problems/missing-ranges
 * <p>
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its
 * missing ranges.
 * 
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 * 
 * </p>
 * 
 * @author abel created on 2018/2/27 下午11:00
 * @version $Id$
 */
public class MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] == lower) {
                    continue;
                }
                if (nums[i] > lower) {
                    sb.append(lower);
                }
                if (nums[i] > lower + 1) {
                    sb.append("->").append(nums[i] - 1);
                }
            } else if (nums[i] == nums[i - 1] + 1) {
                continue;
            } else {
                if (nums[i - 1] + 2 == nums[i]) {
                    sb.append(nums[i - 1] + 1);
                } else {
                    sb.append(nums[i - 1] + 1).append("->").append(nums[i] - 1);
                }
            }
            res.add(sb.toString());
            sb.delete(0, sb.length());
        }
        if (nums[nums.length - 1] < upper) {
            sb.append(nums[nums.length - 1] + 1);
        }
        if (nums[nums.length - 1] + 1 < upper) {
            sb.append("->").append(upper);
        }
        res.add(sb.toString());

        return res;
    }
}
