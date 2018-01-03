/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 254. Factor Combinations
 * 
 * <p>
 * Numbers can be regarded as product of its factors. For example,
 * 
 * 8 = 2 x 2 x 2; = 2 x 4. Write a function that takes an integer n and return all possible combinations of its factors.
 * 
 * Note: You may assume that n is always positive. Factors should be greater than 1 and less than n.
 * </p>
 * 
 * @author abel created on 2018/1/3 下午2:38
 * @version $Id$
 */
public class FactorCombinations {

    public List<List<Integer>> getFactors(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        helper(res, 2, n, new ArrayList<>());
        return res;
    }

    private void helper(List<List<Integer>> res, int start, int n, List<Integer> list) {
        if (n <= 1 && list.size() > 1) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                helper(res, i, n / i, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        FactorCombinations solution = new FactorCombinations();
        System.out.println(solution.getFactors(8));
    }
}
