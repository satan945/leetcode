/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * <p>
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the
 * candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note: All numbers (including target) will be positive integers. The solution set must not contain duplicate
 * combinations. For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * </p>
 * 
 * @author abel created on 2018/1/3 下午2:14
 * @version $Id$
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        helper(res, candidates, 0, target, new ArrayList<>());
        return res;
    }

    private void helper(List<List<Integer>> res, int[] candidates, int pos, int target, List<Integer> list) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > pos && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target - candidates[i] >= 0) {
                list.add(candidates[i]);
                helper(res, candidates, i + 1, target - candidates[i], list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSumII solution = new CombinationSumII();
//        System.out.println(solution.combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8));
        List<List<Integer>> res = solution.combinationSum2(new int[] { 9, 7, 0, 3, 9, 8, 6, 5, 7, 6 }, 29);
        System.out.println(res.size()+":\n"+res);
    }
}
