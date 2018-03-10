/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

/**
 * 789. Escape The Ghosts
 *
 * https://leetcode.com/problems/escape-the-ghosts/description/
 * 
 * @author abel created on 2018/3/2 下午4:35
 * @version $Id$
 */
public class EscapeTheGhosts {

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        if (ghosts == null || ghosts.length == 0) {
            return true;
        }
        if (target == null || target.length == 0) {
            return false;
        }
        int distance = calDistance(new int[] { 0, 0 }, target);
        for (int i = 0; i < ghosts.length; i++) {
            int gDistance = calDistance(ghosts[i], target);
            if (gDistance <= distance) {
                return false;
            }
        }
        return true;

    }

    private int calDistance(int[] ghost, int[] target) {
        return Math.abs(target[0] - ghost[0]) + Math.abs(target[1] - ghost[1]);
    }

    public static void main(String[] args) {
        EscapeTheGhosts escapeTheGhosts = new EscapeTheGhosts();
        System.out.println(escapeTheGhosts.escapeGhosts(
                new int[][] { { 1, 9 }, { 2, -5 }, { 3, 8 }, { 9, 8 }, { -1, 3 } }, new int[] { 8, -10 }));
    }
}
