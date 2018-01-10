/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

/**
 * 667. Beautiful Arrangement II
 * 
 * @author abel created on 2018/1/10 下午3:10
 * @version $Id$
 */
public class BeautifulArrangementII {

    public int[] constructArray(int n, int k) {
        int i = 1, j = n;
        int[] res = new int[n];
        for (int m = 0; i <= j; m++) {
            res[m] = k % 2 != 0 ? i++ : j--;
            if (k > 1) {
                k--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BeautifulArrangementII solution = new BeautifulArrangementII();
        System.out.println(solution.constructArray(9, 8));
    }

}
