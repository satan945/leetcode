/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.dfs.medium;

/**
 * @author Abel created on 2017/9/1 15:06
 * @version $Id$
 */
public class Solution {

    /**
     * 200. Number of Islands
     * <p>
     * https://leetcode.com/problems/number-of-islands
     * <p>
     * 
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by
     * water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the
     * grid are all surrounded by water.
     * 
     * Example 1:
     * 
     * 11110
     * 
     * 11010
     * 
     * 11000
     * 
     * 00000
     * 
     * Answer: 1
     * 
     * Example 2:
     * 
     * 11000
     * 
     * 11000
     * 
     * 00100
     * 
     * 00011
     * 
     * Answer: 3
     * 
     * Credits: Special thanks to @mithmatt for adding this problem and creating all test cases.
     * </p>
     * 
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfsMark(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfsMark(char[][] grid, int i, int j) {
        int[][] move = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i > m || j > n || grid[i][j] != '1') {
            return;
        }
        for (int k = 0; k < move.length; k++) {
            grid[i][j] = 0;
            dfsMark(grid, move[k][0], move[k][1]);
        }
    }

    /**
     * 130. Surrounded Regions
     * <p>
     * https://leetcode.com/problems/surrounded-regions
     * <p>
     * 
     * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
     * 
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * 
     * For example,
     * 
     * X X X X
     * 
     * X O O X
     * 
     * X X O X
     * 
     * X O X X
     * 
     * After running your function, the board should be:
     * 
     * X X X X
     * 
     * X X X X
     * 
     * X X X X
     * 
     * X O X X
     * </p>
     * 
     * @param board
     */
    public void solve(char[][] board) {

    }
}
