/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.cracking189.dp;

/**Page 134
 * @author Abel created on 2018/2/7 22:16
 * @version $Id$
 */
public class TripleSteps {

    public int stepCounts(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        TripleSteps steps = new TripleSteps();
        System.out.println(steps.stepCounts(4));
    }
}
