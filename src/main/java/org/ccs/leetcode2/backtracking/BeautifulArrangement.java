/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

/**
 * 526. Beautiful Arrangement
 * <p>
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N
 * numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
 * 
 * The number at the ith position is divisible by i. i is divisible by the number at the ith position. Now given N, how
 * many beautiful arrangements can you construct?
 * </p>
 * 
 * @author abel created on 2018/1/3 下午12:43
 * @version $Id$
 */
public class BeautifulArrangement {

    public int countArrangement(int N) {
        boolean[] used = new boolean[N + 1];
        int[] res = new int[1];
        helper(1, N, res, used);
        return res[0];
    }

    private void helper(int pos, int n, int[] res, boolean[] used) {
        if (pos > n) {
            res[0]++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!used[i] && (i % pos == 0 || pos % i == 0)) {
                used[i] = true;
                helper(pos + 1, n, res, used);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        BeautifulArrangement solution = new BeautifulArrangement();
        System.out.println(solution.countArrangement(2));
    }
}
