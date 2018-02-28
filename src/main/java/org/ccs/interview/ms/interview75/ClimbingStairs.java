/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

/**
 * 70. Climbing Stairs
 * 
 * @author abel created on 2018/2/23 下午3:47
 * @version $Id$
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] res = new int[n + 1];
        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 2] + res[i - 1];
        }
        return res[n];
    }

    public int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int first = 1;
        int second = 2;
        int res = -1;
        for (int i = 3; i <= n; i++) {
            res = first + second;
            first = second;
            second = res;
        }
        return res;
    }
}
