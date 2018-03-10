/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 85. Maximal Rectangle
 * 
 * @author abel created on 2018/3/2 下午4:44
 * @version $Id$
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] areas = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    areas[j] = 0;
                } else {
                    areas[j] += 1;
                }
            }
            res = Math.max(res, maxHistogram(areas));
        }
        return res;
    }

    private int findMaxArea(int[] areas) {
        int res = 0;
        int height;
        for (int i = 0; i < areas.length - 1; i++) {
            height = Integer.MAX_VALUE;
            for (int j = i + 1; j < areas.length; j++) {
                height = Math.min(height, areas[j]);
                res = Math.max(height * (j - i), res);
            }
        }
        return res;
    }

    public int maxHistogram2(int input[]) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int i;
        for (i = 0; i < input.length;) {
            if (stack.isEmpty() || input[stack.peek()] <= input[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    maxArea = Math.max(input[top] * i, maxArea);
                } else {
                    maxArea = Math.max(input[top] * (i - stack.peek() - 1), maxArea);
                }
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (stack.isEmpty()) {
                maxArea = Math.max(input[top] * i, maxArea);
            } else {
                maxArea = Math.max(input[top] * (i - stack.peek() - 1), maxArea);
            }
        }
        return maxArea;
    }

    private int maxHistogram(int input[]) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int maxArea = 0;
        int area = 0;
        int i;
        for (i = 0; i < input.length;) {
            if (stack.isEmpty() || input[stack.peekFirst()] <= input[i]) {
                stack.offerFirst(i++);
            } else {
                int top = stack.pollFirst();
                // if stack is empty means everything till i has to be
                // greater or equal to input[top] so get area by
                // input[top] * i;
                if (stack.isEmpty()) {
                    area = input[top] * i;
                }
                // if stack is not empty then everythin from i-1 to input.peek() + 1
                // has to be greater or equal to input[top]
                // so area = input[top]*(i - stack.peek() - 1);
                else {
                    area = input[top] * (i - stack.peekFirst() - 1);
                }
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pollFirst();
            // if stack is empty means everything till i has to be
            // greater or equal to input[top] so get area by
            // input[top] * i;
            if (stack.isEmpty()) {
                area = input[top] * i;
            }
            // if stack is not empty then everything from i-1 to input.peek() + 1
            // has to be greater or equal to input[top]
            // so area = input[top]*(i - stack.peek() - 1);
            else {
                area = input[top] * (i - stack.peekFirst() - 1);
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' } };
        MaximalRectangle maximalRectangle = new MaximalRectangle();

        int[] input = new int[] { 3, 2, 4, 1, 0, 3, 0, 3, 3, 3 };
        System.out.println(maximalRectangle.maxHistogram2(input));
        System.out.println(maximalRectangle.maxHistogram(input));
        // System.out.println(maximalRectangle.maximalRectangle(matrix));

    }
}
