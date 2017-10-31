/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.binarysearch.medium;

/**
 * @author abel created on 2017/10/19 下午7:41
 * @version $Id$
 */
public class Solution {

    /**
     * 240. Search a 2D Matrix II
     * <p>
     * https://leetcode.com/problems/search-a-2d-matrix-ii
     * <p>
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following
     * properties:
     * 
     * Integers in each row are sorted in ascending from left to right. Integers in each column are sorted in ascending
     * from top to bottom.
     * </p>
     * 
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
        int row = 0;
        int col = n - 1;
        while (col >= 0 && row <= m - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] nums = { //
                { 1, 4, 7, 11, 15 }, //
                { 2, 5, 8, 12, 19 }, //
                { 3, 6, 9, 16, 22 }, //
                { 10, 13, 14, 17, 24 }, //
                { 18, 21, 23, 26, 30 } //
        };
        Solution solution = new Solution();
        System.out.println(solution.searchMatrix(nums, 15));
    }
}
