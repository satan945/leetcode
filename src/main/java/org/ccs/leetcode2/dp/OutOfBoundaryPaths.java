/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

/**
 * 576. Out of Boundary Paths
 * 
 * <p>
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent
 * cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times.
 * Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod
 * 109 + 7.
 * </p>
 * 
 * @author Abel created on 2018/1/5 16:51
 * @version $Id$
 */
public class OutOfBoundaryPaths {
    private int[] moves = new int[] { 1, 0, -1, 0, 1 };
    private final int M = 1000000007;

    public int findPaths(int m, int n, int N, int i, int j) {
        int[][] dp = new int[m][n];
        dp[i][j] = 1;
        int count = 0;
        for (int step = 1; step <= N; step++) {
            int[][] tmp = new int[m][n];
            for (int y = 0; y < m; y++) {
                for (int x = 0; x < n; x++) {
                    for (int move = 0; move < moves.length - 1; move++) {
                        int nx = x + moves[move];
                        int ny = y + moves[move + 1];
                        if (nx >= n || nx < 0 || ny >= m || ny < 0) {
                            count = (count + dp[y][x]) % M;
                        } else {
                            tmp[ny][nx] = (tmp[ny][nx] + dp[y][x]) % M;
                        }
                    }
                }
            }
            dp = tmp;
        }
        return count;
    }

    public static void main(String[] args) {
        OutOfBoundaryPaths solution = new OutOfBoundaryPaths();
        System.out.println(solution.findPaths(2, 2, 2, 0, 0));
    }
}
