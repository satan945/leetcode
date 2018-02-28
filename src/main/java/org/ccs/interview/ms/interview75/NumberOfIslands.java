/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

/**
 * @author abel created on 2018/2/27 下午4:48
 * @version $Id$
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        int[] moves = new int[] { 1, 0, -1, 0, 1 };
        grid[i][j] = '2';
        for (int m = 0; m < moves.length - 1; m++) {
            dfs(grid, i + moves[m], j + moves[m + 1]);
        }
    }


}
