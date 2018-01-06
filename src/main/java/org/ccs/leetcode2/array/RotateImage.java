/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

/**
 * 48. Rotate Image
 * <p>
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Note: You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT
 * allocate another 2D matrix and do the rotation.
 * </p>
 * 
 * @author Abel created on 2018/1/5 15:29
 * @version $Id$
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            int j = 0, k = matrix[i].length - 1;
            while (j < k) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = temp;
                j++;
                k--;
            }
        }
        System.out.println("aaa");
    }

    public static void main(String[] args) {
        int[][] input = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        RotateImage solution = new RotateImage();
        solution.rotate(input);
    }
}
