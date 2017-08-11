/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.array.medium;

import org.ccs.leetcode.bean.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Abel created on 2017/7/10 17:47
 * @version $Id$
 */
public class Solution {
    /**
     * 48. Rotate Image
     * <p>
     * https://leetcode.com/problems/rotate-image
     * <p>
     * You are given an n x n 2D matrix representing an image.
     * 
     * Rotate the image by 90 degrees (clockwise).
     * 
     * Follow up: Could you do this in-place?
     * </p>
     * 
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m != n) {
            return;
        }
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[n - j - 1][i];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = result[i][j];
            }
        }
    }

    /**
     * <p>
     * The idea was firstly transpose the matrix and then flip it symmetrically. For instance, 1 2 3 4 5 6 7 8 9 after
     * transpose, it will be swap(matrix[i][j], matrix[j][i]) 1 4 7 2 5 8 3 6 9 Then flip the matrix horizontally.
     * (swap(matrix[i][j], matrix[i][matrix.length-1-j]) 7 4 1 8 5 2 9 6 3
     * </p>
     * 
     * @param matrix
     */
    public void rotateInPlace(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    /**
     * 11. Container With Most Water
     * 
     * <p>
     * https://leetcode.com/problems/container-with-most-water *
     * <p>
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical
     * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
     * with x-axis forms a container, such that the container contains the most water.
     * 
     * Note: You may not slant the container and n is at least 2.
     * </p>
     * 
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int max = 0;
        while (j > i) {
            int l = j - i;
            int w = Math.min(height[i], height[j]);
            if (l * w > max) {
                max = l * w;
            }
            if (w == height[i]) {
                i++;
            } else if (w == height[j]) {
                j--;
            }
        }
        return max;
    }

    /**
     * 370. Range Addition
     * <p>
     * https://leetcode.com/problems/range-addition
     * <p>
     * Assume you have an array of length n initialized with all 0's and are given k update operations.
     * 
     * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray
     * A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
     * 
     * Return the modified array after all k operations were executed.
     * 
     * Example:
     * 
     * Given:
     * 
     * length = 5, updates = [ [1, 3, 2], [2, 4, 3], [0, 2, -2] ]
     * 
     * Output:
     * 
     * [-2, 0, 3, 5, 3]
     * 
     * 
     * Explanation:
     * 
     * Initial state: [ 0, 0, 0, 0, 0 ]
     * 
     * After applying operation [1, 3, 2]: [ 0, 2, 2, 2, 0 ]
     * 
     * After applying operation [2, 4, 3]: [ 0, 2, 5, 5, 3 ]
     * 
     * After applying operation [0, 2, -2]: [-2, 0, 3, 5, 3 ]
     * </p>
     * https://discuss.leetcode.com/topic/53142/detailed-explanation-if-you-don-t-understand-especially-put-negative-inc-at-endindex-1
     * 
     * @param length
     * @param updates
     * @return
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int value = update[2];

            result[start] += value;
            if (end + 1 < length) {
                result[end + 1] = -value;
            }
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += result[i];
            result[i] = sum;
        }
        return result;
    }

    /**
     * 531. Lonely Pixel I
     * <p>
     * https://leetcode.com/problems/lonely-pixel-i/description/
     * <p>
     * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
     * 
     * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels
     * respectively.
     * 
     * A black lonely pixel is character 'B' that located at a specific position where the same row and same column
     * don't have any other black pixels.
     * 
     * Example: Input: [['W', 'W', 'B'], ['W', 'B', 'W'], ['B', 'W', 'W']]
     * 
     * Output: 3 Explanation: All the three 'B's are black lonely pixels. Note: The range of width and height of the
     * input 2D array is [1,500].
     * </p>
     * 
     * @param picture
     * @return
     */
    public int findLonelyPixel(char[][] picture) {
        return 0;
    }

    /**
     * 31. Next Permutation
     * <p>
     * https://leetcode.com/problems/next-permutation
     * <p>
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of
     * numbers.
     * 
     * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
     * order).
     * 
     * The replacement must be in-place, do not allocate extra memory.
     * 
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
     * column.
     * 
     * 1,2,3 → 1,3,2
     * 
     * 3,2,1 → 1,2,3
     * 
     * 1,1,5 → 1,5,1
     * </p>
     * todo
     * 
     * @param nums
     */
    public void nextPermutation(int[] nums) {

    }

    /**
     * 229. Majority Element II
     * <p>
     * https://leetcode.com/problems/majority-element-ii
     * <p>
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run
     * in linear time and in O(1) space.
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0;
        int count2 = 0;
        int res1 = 0;
        int res2 = 0;
        for (int num : nums) {
            if (num == res1) {
                count1++;
            } else if (num == res2) {
                count2++;
            } else if (count1 == 0) {
                count1 = 1;
                res1 = num;
            } else if (count2 == 0) {
                count2 = 1;
                res2 = num;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == res1) {
                count1++;
            } else if (num == res2) {
                count2++;
            }
        }
        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) {
            result.add(res1);
        }
        if (count2 > nums.length / 3) {
            result.add(res2);
        }
        return result;
    }

    /**
     * 611. Valid Triangle Number
     * <p>
     * https://leetcode.com/problems/valid-triangle-number
     * <p>
     * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the
     * array that can make triangles if we take them as side lengths of a triangle.
     * 
     * Example 1:
     * 
     * Input: [2,2,3,4]
     * 
     * Output: 3
     * 
     * Explanation: Valid combinations are: 2,3,4 (using the first 2) 2,3,4 (using the second 2) 2,2,3 Note: The length
     * of the given array won't exceed 1000. The integers in the given array are in the range of [0, 1000].
     * 
     * </p>
     * https://leetcode.com/problems/valid-triangle-number/solution/#approach-2-using-binary-search-accepted
     * 
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int k = j + 1;
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                count += k - j - 1;
            }

        }
        return count;
    }

    /**
     * 54. Spiral Matrix
     * <p>
     * https://leetcode.com/problems/spiral-matrix
     * <p>
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * 
     * For example, Given the following matrix:
     * 
     * [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] You should return [1,2,3,6,9,8,7,4,5].
     * </p>
     * 
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }
        int rowBegin = 0;
        int colBegin = 0;
        int rowEnd = matrix.length - 1;
        int colEnd = matrix[0].length - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int j = colBegin; j <= colEnd; j++) {
                result.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            for (int j = rowBegin; j <= rowEnd; j++) {
                result.add(matrix[j][colEnd]);
            }
            colEnd--;
            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j--) {
                    result.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j--) {
                    result.add(matrix[j][colBegin]);
                }
            }
            colBegin++;
        }
        return result;

    }

    /**
     * 
     * 59. Spiral Matrix II
     *
     * <p>
     * https://leetcode.com/problems/spiral-matrix-ii
     * <p>
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * 
     * For example, Given n = 3,
     * 
     * You should return the following matrix: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
     * </p>
     * 
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int val = 1;
        int rowBegin = 0;
        int colBegin = 0;
        int rowEnd = n - 1;
        int colEnd = n - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // travel right
            for (int j = colBegin; j <= colEnd; j++) {
                result[rowBegin][j] = val++;
            }
            rowBegin++;
            // travel down
            for (int i = rowBegin; i <= rowEnd; i++) {
                result[i][colEnd] = val++;
            }
            colEnd--;
            // travel left
            if (rowBegin <= rowEnd) {
                for (int j = colEnd; j >= colBegin; j--) {
                    result[rowEnd][j] = val++;
                }
            }
            rowEnd--;
            // travel up
            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    result[i][colBegin] = val++;
                }
            }
            colBegin++;
        }
        return result;

    }

    /**
     * 56. Merge Intervals
     * <p>
     * https://leetcode.com/problems/merge-intervals
     * 
     * <p>
     * Given a collection of intervals, merge all overlapping intervals.
     * 
     * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
     * </p>
     * 
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start < o2.start) {
                    return -1;
                }
                if (o1.start > o2.start) {
                    return 1;
                }
                return 0;
            }
        });
        if (intervals.size() <= 1) {
            return intervals;
        }
        List<Interval> res = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            if (end >= interval.start) {
                end = Math.max(interval.end, end);
            } else {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        // solution.rotate(matrix);
        System.out.println(solution.spiralOrder(matrix));
        System.out.println(solution.generateMatrix(3));

        // int[] array = new int[] { 2, 2, 3, 4, };
        // System.out.println(solution.triangleNumber(array));
    }
}
