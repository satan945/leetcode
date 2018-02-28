/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 254. Factor Combinations
 * <p>
 * Numbers can be regarded as product of its factors. For example,
 * 
 * 8 = 2 x 2 x 2; = 2 x 4. Write a function that takes an integer n and return all possible combinations of its factors.
 * </p>
 * 
 * @author abel created on 2018/2/26 下午4:48
 * @version $Id$
 */
public class FactorCombinations {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        calFactorLists(n, res, 2, new ArrayList<>());
        return res;

    }

    private void calFactorLists(int n, List<List<Integer>> res, int fac, List<Integer> list) {
        if (n <= 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i = fac; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                calFactorLists(n / i, res, i, list);
                list.remove(list.size() - 1);
            }
        }

    }


    public static void main(String[] args) {
        FactorCombinations factorCombinations =new FactorCombinations();
        System.out.println(factorCombinations.getFactors(12));
    }
}
