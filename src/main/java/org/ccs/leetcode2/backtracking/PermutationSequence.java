/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. Permutation Sequence
 * 
 * <p>
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, We get the following sequence (ie, for n = 3):
 * 
 * "123" "132" "213" "231" "312" "321" Given n and k, return the kth permutation sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * </p>
 * 
 * @author abel created on 2018/1/3 上午11:05
 * @version $Id$
 */
public class PermutationSequence {

    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int fac = 1;
        for (int i = 2; i <= n - 1; i++) {
            fac *= i;
        }
        k--;
        StringBuilder sb = new StringBuilder();
        int round = n - 1;
        while (round >= 0) {
            int num = list.get(k / fac);
            sb.append(num);
            list.remove(k / fac);
            if (round > 0) {
                k %= fac;
                fac /= round;
            }
            round--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PermutationSequence solution = new PermutationSequence();
        System.out.println(solution.getPermutation(3, 6));
    }

}
