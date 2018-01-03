/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 * <p>
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
 * used and each combination should be a unique set of numbers.
 * </p>
 * 
 * @author abel created on 2018/1/3 上午11:44
 * @version $Id$
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, k, 1, 9, n, new ArrayList<>());
        return res;
    }

    private void helper(List<List<Integer>> res, int k, int start, int end, int target, List<Integer> list) {
        if (target == 0 && k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (k == 0) {
            return;
        }
        for (int i = start; i <= end; i++) {
            if (target - i >= 0) {
                list.add(i);
                helper(res, k - 1, i + 1, end, target - i, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSumIII solution = new CombinationSumIII();
        System.out.println(solution.combinationSum3(3, 9));
    }
}
