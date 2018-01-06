/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

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
    private int count = 0;
    private int[] moves = new int[] { 1, 0, -1, 0, 1 };

    public int findPaths(int m, int n, int N, int i, int j) {
        if (m == 0 || n == 0 || N <= 0 || i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        dfsFindPath(m, n, N, i, j);
        return count;
    }

    private void dfsFindPath(int m, int n, int steps, int i, int j) {
        if (steps >= 0 && (i >= m || i < 0 || j >= n || j < 0)) {
            count++;
            return;
        }
        if (steps < 0) {
            return;
        }
        for (int k = 0; k < moves.length - 1; k++) {
            dfsFindPath(m, n, steps - 1, i + moves[k], j + moves[k + 1]);
        }
    }

    public static void main(String[] args) {
        OutOfBoundaryPaths solution = new OutOfBoundaryPaths();
        System.out.println(solution.findPaths(2, 2, 2, 0, 0));
    }
}
