/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest63;

/**
 * 
 * 750. Number Of Corner Rectangles
 *
 *
 * @author abel created on 2018/1/30 下午4:37
 * @version $Id$
 */
public class NumberOfCornerRectangles {
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

    public static void main(String[] args) {
        int[][] grid = { { 1, 0, 0, 1, 0 }, { 0, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0 }, { 1, 0, 1, 0, 1 } };
        int[][] grid1 = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
        int[][] grid2 = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 } };
        int[][] grid3 = { { 1, 0, 1 }, { 0, 1, 1 }, { 1, 1, 1 }, { 1, 0, 1 } };
        NumberOfCornerRectangles solution = new NumberOfCornerRectangles();
        System.out.println(solution.countCornerRectangles(grid3));
    }
}
