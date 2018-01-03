/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. Combination Sum
 * <p>
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C
 * where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note: All numbers (including target) will be positive integers. The solution set must not contain duplicate
 * combinations.
 * </p>
 * 
 * @author abel created on 2018/1/2 上午10:50
 * @version $Id$
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(res, target, candidates, new ArrayList<>());
        return res;
    }

    private void helper(List<List<Integer>> res, int target, int[] candidates, List<Integer> list) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if ((list.size() == 0 || candidates[i] >= list.get(list.size() - 1)) && target - candidates[i] >= 0) {
                list.add(candidates[i]);
                helper(res, target - candidates[i], candidates, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        System.out.println(solution.combinationSum(new int[] { 2, 3, 6, 7 }, 7));
    }
}
