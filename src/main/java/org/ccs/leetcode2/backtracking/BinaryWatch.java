/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. Binary Watch
 * <p>
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the
 * minutes (0-59).
 * 
 * Each LED represents a zero or one, with the least significant bit on the right.
 * </p>
 * 
 * @author abel created on 2018/1/3 下午3:23
 * @version $Id$
 */
public class BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] HOURS = new int[] { 1, 2, 4, 8 };
        int[] MINS = new int[] { 1, 2, 4, 8, 16, 32 };
        for (int i = 0; i <= num; i++) {
            List<Integer> hourList = helper(HOURS, i);
            List<Integer> minList = helper(MINS, num - i);
            for (int hour : hourList) {
                if (hour >= 12) {
                    continue;
                }
                for (int min : minList) {
                    if (min >= 60) {
                        continue;
                    }
                    res.add(hour + ":" + (min < 10 ? "0" + min : min));
                }
            }
        }
        return res;
    }

    private List<Integer> helper(int[] nums, int count) {
        List<Integer> list = new ArrayList<>();
        if (count > nums.length) {
            return list;
        }
        genList(nums, 0, count, 0, list);
        return list;
    }

    private void genList(int[] nums, int pos, int count, int sum, List<Integer> list) {
        if (count == 0) {
            list.add(sum);
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            sum += nums[i];
            genList(nums, i + 1, count - 1, sum, list);
            sum -= nums[i];
        }
    }

    public static void main(String[] args) {
        BinaryWatch solution = new BinaryWatch();
        System.out.println(solution.readBinaryWatch(1));

    }

}
