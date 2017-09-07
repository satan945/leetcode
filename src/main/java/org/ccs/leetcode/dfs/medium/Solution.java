/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.dfs.medium;

import org.ccs.leetcode.bean.ListNode;
import org.ccs.leetcode.bean.TreeNode;

import java.util.Arrays;

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
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int m = board.length, n = board[0].length;
        // start from first and last column
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfsBoundry(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                dfsBoundry(board, i, n - 1);
            }
        }
        // start from first and last row
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfsBoundry(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                dfsBoundry(board, m - 1, j);
            }
        }

        // last loop to change O to X
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfsBoundry(char[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || j > 0 || i >= m || j >= n) {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '*';
        }
        if (i > 1 && board[i - 1][j] == 'O') {
            dfsBoundry(board, i - 1, j);
        }
        if (i < m - 2 && board[i + 1][j] == 'O') {
            dfsBoundry(board, i + 1, j);
        }
        if (j > 1 && board[i][j - 1] == 'O') {
            dfsBoundry(board, i, j - 1);
        }
        if (j < n - 2 && board[i][j + 1] == 'O') {
            dfsBoundry(board, i, j + 1);
        }
    }

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * <p>
     * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree
     * <p>
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     * 
     * </p>
     * 
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        return buildBST(head, null);
    }

    private TreeNode buildBST(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) {
            return null;
        }
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = buildBST(head, slow);
        root.right = buildBST(slow.next, tail);
        return root;
    }

    /**
     * 542. 01 Matrix
     * <p>
     * https://leetcode.com/problems/01-matrix
     * <p>
     * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
     * 
     * The distance between two adjacent cells is 1.
     * 
     * Example 1:
     * 
     * Input:
     * 
     * 0 0 0
     * 
     * 0 1 0
     * 
     * 0 0 0
     * 
     * Output:
     * 
     * 0 0 0
     * 
     * 0 1 0
     * 
     * 0 0 0
     * 
     * Example 2: Input:
     * 
     * 0 0 0
     * 
     * 0 1 0
     * 
     * 1 1 1
     * 
     * Output: 0 0 0
     * 
     * 0 1 0
     * 
     * 1 2 1
     * 
     * Note: The number of elements of the given matrix will not exceed 10,000. There are at least one 0 in the given
     * matrix. The cells are adjacent in only four directions: up, down, left and right.
     * </p>
     * 
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        System.out.println(new Solution().sortedListToBST(head));
    }
}
