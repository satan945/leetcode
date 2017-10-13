/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dfs.easy;

import org.ccs.leetcode.bean.NestedInteger;

import java.util.List;

/**
 * @author abel created on 2017/9/22 下午5:31
 * @version $Id$
 */
public class Solution {

    /**
     * 339. Nested List Weight Sum
     * <p>
     * https://leetcode.com/problems/nested-list-weight-sum
     * <p>
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
     * 
     * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
     * 
     * Example 1: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
     * 
     * Example 2: Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 +
     * 4*2 + 6*3 = 27)
     * </p>
     * 
     * @param nestedList
     * @return
     */
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int res = 0;
        for (NestedInteger nestedInteger : nestedList) {
            int level = 1;
            res += calDepthSum(nestedInteger, level);
        }
        return res;
    }

    private int calDepthSum(NestedInteger nestedInteger, int level) {
        int res = 0;
        if (nestedInteger.isInteger()) {
            return nestedInteger.getInteger() * level;
        } else {
            for (NestedInteger integer : nestedInteger.getList()) {
                res += calDepthSum(integer, level + 1);
            }
        }
        return res;
    }

    /**
     * 695. Max Area of Island
     * 
     * <p>
     * https://leetcode.com/problems/max-area-of-island
     * <p>
     * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected
     * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
     * 
     * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
     * </p>
     * 
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfsArea(grid, i, j, 0);
                    res = Math.max(area, res);
                }
            }
        }
        return res;
    }

    private int dfsArea(int[][] grid, int i, int j, int area) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i > m || j < 0 || j > n || grid[i][j] == 0) {
            return area;
        }
        grid[i][j] = 0;
        area++;
        area = dfsArea(grid, i + 1, j, area);
        area = dfsArea(grid, i - 1, j, area);
        area = dfsArea(grid, i, j + 1, area);
        area = dfsArea(grid, i, j - 1, area);
        return area;
    }
}
