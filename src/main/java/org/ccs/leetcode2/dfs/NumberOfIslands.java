/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

/**
 * 200. Number of Islands
 * <p>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 * </p>
 * 
 * @author abel created on 2018/1/3 下午3:35
 * @version $Id$
 */
public class NumberOfIslands {
    final int[] moves = new int[] { 1, 0, -1, 0, 1 };

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, m, n, i, j);
                }
            }
        }
        return count;

    }

    private void dfs(char[][] grid, int m, int n, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        grid[i][j] = '*';
        for (int move = 0; move < moves.length - 1; move++) {
            int newI = i + moves[move];
            int newJ = j + moves[move + 1];
            if (canMove(grid, newI, newJ, m, n)) {
                dfs(grid, m, n, newI, newJ);
            }
        }
    }

    private boolean canMove(char[][] grid, int i, int j, int m, int n) {
        if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
