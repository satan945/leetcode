/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.contest;

/**
 * Weekly Contest 63
 * 
 * @author Abel created on 2017/12/16 18:51
 * @version $Id$
 */
public class Solution {
    /**
     * 750. Number Of Corner Rectangles
     * 
     * @param grid
     * @return
     */

    public int countCornerRectangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        MatrixNode[] lastNodes = new MatrixNode[m];
        MatrixNode[] firstNodes = new MatrixNode[m];
        int count = 0;
        boolean foundFirst;
        MatrixNode preNode;
        for (int i = 0; i < m; i++) {
            foundFirst = false;
            preNode = null;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    MatrixNode node = new MatrixNode();
                    node.y = i;
                    node.x = j;
                    if (!foundFirst) {
                        firstNodes[i] = node;
                        foundFirst = true;
                    } else {
                        node.pre = preNode;
                    }
                    if (preNode != null) {
                        preNode.post = node;
                    }
                    lastNodes[i] = node;
                    preNode = node;
                }
            }
        }
        for (MatrixNode node : firstNodes) {
            while (node != null) {
                int y = node.y;
                int x = node.x;
                for (int i = m - 1; i > y; i--) {
                    MatrixNode last = lastNodes[i];
                    while (last != null) {
                        if (last.y <= node.y || last.x <= node.x) {
                            break;
                        }
                        int lastY = last.y;
                        int lastX = last.x;
                        if (grid[y][lastX] == 1 && grid[lastY][x] == 1) {
                            count++;
                        }
                        last = last.pre;
                    }
                }
                node = node.post;
            }
        }
        return count;
    }

    public class MatrixNode {
        public MatrixNode pre;
        public MatrixNode post;

        public int x;
        public int y;

        public MatrixNode() {
            this.x = -1;
            this.y = -1;
            pre = null;
            post = null;
        }
    }

    /**
     * 748. Shortest Completing Word
     * 
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        String target = licensePlate.toLowerCase();
        String res = null;
        int[] targetCount = buildCount(target);
        for (String word : words) {
            int[] wordCount = buildCount(word);
            if (match(wordCount, targetCount) && (res == null || res.length() > word.length())) {
                res = word;
            }
        }
        return res;
    }

    private int[] buildCount(String target) {
        int[] count = new int[26];
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                count[ch - 'a']++;
            }
        }
        return count;
    }

    private boolean match(int[] wordCount, int[] targetCount) {
        for (int i = 0; i < 26; i++) {
            if (wordCount[i] < targetCount[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = { { 1, 0, 0, 1, 0 }, { 0, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0 }, { 1, 0, 1, 0, 1 } };
        int[][] grid1 = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
        int[][] grid2 = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 } };
        int[][] grid3 = { { 1, 0, 1 }, { 0, 1, 1 }, { 1, 1, 1 }, { 1, 0, 1 } };
        Solution solution = new Solution();
        System.out.println(solution.countCornerRectangles(grid3));
    }
}
