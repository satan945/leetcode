/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.interview.google;

/**
 * 361. Bomb Enemy
 * 
 * https://leetcode.com/problems/bomb-enemy
 * 
 * @author Abel created on 2018/3/5 14:20
 * @version $Id$
 */
public class BombEnemy {

    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] rowCount = new int[grid.length];
        int[] colCount = new int[grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j == 0 || grid[i][j - 1] == 'W') {

                }
                if (grid[i][j] == 'E') {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        return res;
    }
}
