/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abel created on 2018/1/2 下午6:50
 * @version $Id$
 */
public class Combinations {
    /**
     * 77. Combinations
     * <p>
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * 
     * </p>
     * 
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(1, n, k, res, new ArrayList<>());
        return res;
    }

    private void helper(int i, int n, int k, List<List<Integer>> res, List<Integer> list) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int j = i; j <= n; j++) {
            list.add(j);
            helper(j + 1, n, k, res, list);
            list.remove(list.size() - 1);
        }

    }

    public static void main(String[] args) {
        Combinations solution = new Combinations();
        System.out.println(solution.combine(4,2));
    }
}
