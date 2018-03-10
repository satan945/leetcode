/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

/**
 * 74. Search a 2D Matrix
 *
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 * 
 * @author abel created on 2018/3/7 下午6:20
 * @version $Id$
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int total = m * n;
        int l = 0, r = total - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int[] pos = trasferPos(mid, m, n);
            if (matrix[pos[0]][pos[1]] == target) {
                return true;
            }
            if (matrix[pos[0]][pos[1]] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    private int[] trasferPos(int mid, int m, int n) {
        int y = mid / n;
        int x = mid % n;
        return new int[] { y, x };
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
        System.out.println(new SearchMatrix().searchMatrix(matrix, 5));
    }
}
