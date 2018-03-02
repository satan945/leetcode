/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.amazon;

import java.util.HashSet;
import java.util.Set;

/**
 * @author abel created on 2018/2/28 下午12:34
 * @version $Id$
 */
public class NumberOfDistinctIslands {

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Set<String> found = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, 0, 0, sb);
                    found.add(sb.toString());
                }
            }
        }
        return found.size();
    }

    private int[] moves = new int[] { 1, 0, -1, 0, 1 };

    private void dfs(int[][] grid, int y, int x, int yPos, int xPos, StringBuilder sb) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[y].length || grid[y][x] != 1) {
            return;
        }
        sb.append(yPos).append(xPos);
        grid[y][x] = 0;
        for (int m = 0; m < moves.length - 1; m++) {
            dfs(grid, y + moves[m], x + moves[m + 1], yPos + moves[m], xPos + moves[m + 1], sb);
        }
    }
}
