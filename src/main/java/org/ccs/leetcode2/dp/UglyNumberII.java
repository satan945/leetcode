/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

/**
 * 264. Ugly Number II
 * <p>
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10,
 * 12 is the sequence of the first 10 ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 * </p>
 * 
 * @author abel created on 2018/1/9 下午6:49
 * @version $Id$
 */
public class UglyNumberII {

    public int nthUglyNumber(int n) {
        int count2 = 0, count3 = 0, count5 = 0;
        int[] ugly = new int[n];
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            int u2 = ugly[count2] * 2;
            int u3 = ugly[count3] * 3;
            int u5 = ugly[count5] * 5;
            int ui = Math.min(Math.min(u2, u3), u5);
            ugly[i] = ui;
            if (ui == u2) {
                count2++;
            }
            if (ui == u3) {
                count3++;
            }
            if (ui == u5) {
                count5++;
            }
        }
        return ugly[n - 1];
    }
}
