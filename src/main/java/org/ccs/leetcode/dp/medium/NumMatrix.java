/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dp.medium;

/**
 * 304. Range Sum Query 2D - Immutable
 * 
 * @author abel created on 2017/11/6 下午9:16
 * @version $Id$
 */
public class NumMatrix {
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            dp = null;
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j + 1] = dp[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (dp == null) {
            return 0;
        }
        int res = 0;
        for (int i = row1; i <= row2; i++) {
            res += dp[i][col2 + 1] - dp[i][col1];
        }
        return res;
    }

}
