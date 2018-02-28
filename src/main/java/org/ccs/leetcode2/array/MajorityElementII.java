/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. Majority Element II
 * <p>
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in
 * linear time and in O(1) space.
 * </p>
 * 
 * @author abel created on 2018/2/23 下午4:30
 * @version $Id$
 */
public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;
        int num1 = nums[0];
        int num2 = nums[0];
        int cnt1 = 1;
        int cnt2 = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                cnt1++;
            } else if (nums[i] == num2) {
                cnt2++;
            } else if (cnt1 == 0) {
                num1 = nums[i];
                cnt1 = 1;
            } else if (cnt2 == 0) {
                num2 = nums[i];
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = 0;
        cnt2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                cnt1++;
            } else if (nums[i] == num2) {
                cnt2++;
            }
        }
        if (cnt1 > len / 3) {
            res.add(num1);
        }
        if (cnt2 > len / 3) {
            res.add(num2);
        }
        return res;
    }
}
