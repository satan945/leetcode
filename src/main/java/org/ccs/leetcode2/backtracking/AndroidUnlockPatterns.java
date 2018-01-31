/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

/**
 * 351. Android Unlock Patterns
 * <p>
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock
 * patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 * 
 * Rules for a valid pattern: Each pattern must connect at least m keys and at most n keys. All the keys must be
 * distinct. If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys
 * must have previously selected in the pattern. No jumps through non selected key is allowed. The order of keys used
 * matters.
 * </p>
 * 
 * @author abel created on 2018/1/3 下午2:35
 * @version $Id$
 */
public class AndroidUnlockPatterns {

    public int numberOfPatterns(int m, int n) {
        int[][] jump = new int[10][10];
        jump[1][3] = jump[3][1] = 2;
        jump[1][7] = jump[7][1] = 4;
        jump[3][9] = jump[9][3] = 6;
        jump[7][9] = jump[9][7] = 8;
        jump[1][9] = jump[9][1] = jump[3][7] = jump[7][3] = jump[2][8] = jump[8][2] = jump[4][6] = jump[6][4] = 5;
        boolean[] visited = new boolean[10];
        int res = 0;
        for (int i = m; i <= n; i++) {
            res += calPath(1, jump, visited, i - 1) * 4; // 1,3,7,9 same
            res += calPath(2, jump, visited, i - 1) * 4;// 2,4,6,8 same
            res += calPath(5, jump, visited, i - 1);// 5
        }
        return res;
    }

    private int calPath(int start, int[][] jump, boolean[] visited, int count) {
        if (count < 0) {
            return 0;
        }
        if (count == 0) {
            return 1;
        }
        int res = 0;
        visited[start] = true;
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && (jump[start][i] == 0 || visited[jump[start][i]])) {
                res += calPath(i, jump, visited, count - 1);
            }
        }
        visited[start] = false;
        return res;
    }
}
