/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * code题目是在矩阵里的2sum 输出一对坐标 考虑一下各种可能情况比如溢出和输入小数即可 followup是输出所有坐标
 * 
 * @author abel created on 2018/2/23 下午3:10
 * @version $Id$
 */
public class TwoSumMultiple {

    public List<int[]> findAll2Sum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexMap.putIfAbsent(nums[i], new ArrayList<>());
            indexMap.get(nums[i]).add(i);
        }
        List<int[]> res = new ArrayList<>();
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            if (indexMap.containsKey(target - nums[i])) {
                used.add(nums[i]);
                used.add(target - nums[i]);
                List<Integer> list1 = indexMap.get(nums[i]);
                List<Integer> list2 = indexMap.get(target - nums[i]);
                for (int index1 : list1) {
                    for (int index2 : list2) {
                        res.add(new int[] { index1, index2 });
                    }
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 2, 4, 4, 1 };
        TwoSumMultiple solution = new TwoSumMultiple();
        List<int[]> res = solution.findAll2Sum(nums, 5);
        System.out.println(res);
    }
}
