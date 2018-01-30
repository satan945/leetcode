/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

import java.util.Arrays;

/**
 * 322. Coin Change
 * 
 * @author Abel created on 2018/1/25 23:26
 * @version $Id$
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int coin : coins) {
            if (coin == amount) {
                return 1;
            }
        }
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] >= 0) {
                    dp[i] = dp[i] == -1 ? dp[i - coin] + 1 : Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(new int[] { 1, 2, 5 }, 11));
    }
}
