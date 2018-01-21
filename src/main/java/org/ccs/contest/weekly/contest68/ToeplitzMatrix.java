/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest68;

/**
 * 766. Toeplitz Matrix
 * 
 * @author abel created on 2018/1/20 下午6:32
 * @version $Id$
 */
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 1 || n == 1) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            int num = matrix[0][i];
            int j = 1, k = i + 1;
            while (j < m && k < n && num == matrix[j][k]) {
                j++;
                k++;
            }
            if (j < m && k < n) {
                return false;
            }
        }

        for (int i = 0; i < m; i++) {
            int num = matrix[i][0];
            int j = i + 1, k = 1;
            while (j < m && k < n && num == matrix[j][k]) {
                j++;
                k++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ToeplitzMatrix solution = new ToeplitzMatrix();
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 1, 2, 3 }, { 9, 5, 1, 2 } };
        int[][] matrix2 = { { 1, 2 }, { 2,2}};
        System.out.println(solution.isToeplitzMatrix(matrix2));
    }
}
