/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.array.medium;

import org.ccs.leetcode.bean.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (nums.length <= 1) {
            return;
        }

        Arrays.sort(nums);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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

    /**
     * 33. Search in Rotated Sorted Array
     * <p>
     * https://leetcode.com/problems/search-in-rotated-sorted-array
     * <p>
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * 
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * 
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * 
     * You may assume no duplicate exists in the array.
     * </p>
     * https://discuss.leetcode.com/topic/3538/concise-o-log-n-binary-search-solution/16
     * 
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int pivot = left;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int realmid = (mid + pivot) % nums.length;
            if (nums[realmid] == target) {
                return realmid;
            } else if (nums[realmid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        int minIdx = findMinIdx(nums);
        if (target == nums[minIdx])
            return minIdx;
        int m = nums.length;
        int start = (target <= nums[m - 1]) ? minIdx : 0;
        int end = (target > nums[m - 1]) ? minIdx : m - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            else if (target > nums[mid])
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    public int findMinIdx(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end])
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    /**
     * 238. Product of Array Except Self
     * <p>
     * https://leetcode.com/problems/product-of-array-except-self
     * <p>
     * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the
     * product of all the elements of nums except nums[i].
     * 
     * Solve it without division and in O(n).
     * 
     * For example, given [1,2,3,4], return [24,12,8,6].
     * </p>
     * 
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        if (nums.length == 0) {
            return res;
        }
        int beforeFactor = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = beforeFactor;
            beforeFactor *= nums[i];
        }
        int afterFactor = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= afterFactor;
            afterFactor *= nums[i];
        }
        return res;

    }

    /**
     * 153. Find Minimum in Rotated Sorted Array
     * <p>
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
     * <p>
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * 
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * 
     * Find the minimum element.
     * 
     * You may assume no duplicate exists in the array.
     * 
     * 
     * </p>
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
     * 
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (m > 0 && nums[m] < nums[m - 1]) {
                return nums[m];
            }
            if (nums[l] <= nums[m] && nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return nums[l];
    }

    /**
     * 228. Summary Ranges
     * <p>
     * https://leetcode.com/problems/summary-ranges
     * <p>
     * Given a sorted integer array without duplicates, return the summary of its ranges.
     * 
     * Example 1: Input: [0,1,2,4,5,7] Output: ["0->2","4->5","7"]
     * 
     * Example 2: Input: [0,2,3,4,6,8,9] Output: ["0","2->4","6","8->9"]
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        res.add("" + nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                String str = res.get(res.size() - 1);
                if (str.length() == 0) {
                    str += nums[i];
                    res.set(res.size() - 1, str);
                } else {
                    String sub = str.substring(0, str.contains("->") ? str.indexOf("-") : str.length());
                    res.set(res.size() - 1, sub + "->" + nums[i]);
                }
            } else {
                res.add("" + nums[i]);
            }
        }
        return res;
    }

    public List<String> summaryRanges2(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; ++j) {
            // check if j + 1 extends the range [nums[i], nums[j]]
            if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                continue;
            // put the range [nums[i], nums[j]] into the list
            if (i == j) {
                summary.add(nums[i] + "");
            } else {
                summary.add(nums[i] + "->" + nums[j]);
            }
            i = j + 1;
        }
        return summary;
    }

    /**
     * 163. Missing Ranges
     * <p>
     * https://leetcode.com/problems/missing-ranges
     * <p>
     * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its
     * missing ranges.
     * 
     * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
     * </p>
     * 
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            if (lower == upper) {
                res.add("" + lower);
            } else {
                res.add(lower + "->" + upper);
            }
            return res;
        }

        if (nums[0] > lower) {
            if (nums[0] - 1 == lower) {
                res.add("" + lower);
            } else {
                res.add(lower + "->" + (nums[0] - 1));
            }
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if ((nums[i + 1] == nums[i] + 1) || nums[i] == nums[i + 1]) {
                continue;
            }
            if (nums[i + 1] - nums[i] == 2) {
                res.add("" + (nums[i] + 1));
            } else {
                res.add((nums[i] + 1) + "->" + (nums[i + 1] - 1));
            }
        }

        if (nums[nums.length - 1] < upper) {
            if (upper - 1 == nums[nums.length - 1]) {
                res.add("" + upper);
            } else {
                res.add((nums[nums.length - 1] + 1) + "->" + upper);
            }
        }

        return res;

    }

    /**
     * 73. Set Matrix Zeroes
     * <p>
     * https://leetcode.com/problems/set-matrix-zeroes
     * <p>
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     * </p>
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int[] m = new int[matrix.length];
        int[] n = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    m[i]++;
                    n[j]++;
                }
            }
        }
        for (int i = 0; i < m.length; i++) {
            if (m[i] > 0) {
                Arrays.fill(matrix[i], 0);
            }
        }
        for (int j = 0; j < n.length; j++) {
            if (n[j] > 0) {
                for (int k = 0; k < matrix.length; k++) {
                    matrix[k][j] = 0;
                }
            }
        }
    }

    /**
     * 289. Game of Life
     * <p>
     * https://leetcode.com/problems/game-of-life
     * <p>
     * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
     * devised by the British mathematician John Horton Conway in 1970."
     * 
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with
     * its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above
     * Wikipedia article):
     * 
     * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * 
     * Any live cell with two or three live neighbors lives on to the next generation.
     * 
     * Any live cell with more than three live neighbors dies, as if by over-population..
     * 
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * 
     * Write a function to compute the next state (after one update) of the board given its current state.
     * 
     * Follow up: Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot
     * update some cells first and then use their updated values to update other cells. In this question, we represent
     * the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area
     * encroaches the border of the array. How would you address these problems?
     * </p>
     * todo
     * 
     * @param board
     */
    public void gameOfLife(int[][] board) {

    }

    /**
     * 287. Find the Duplicate Number
     * <p>
     * https://leetcode.com/problems/find-the-duplicate-number/description/
     * <p>
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at
     * least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     *
     * Note: You must not modify the array (assume the array is read only).
     *
     * You must use only constant, O(1) extra space.
     *
     * Your runtime complexity should be less than O(n2).
     *
     * There is only one duplicate number in the array, but it could be repeated more than once.
     * </p>
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] { 3, 2, 4 };
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        // solution.rotate(matrix);
        // System.out.println(solution.spiralOrder(matrix));
        // System.out.println(solution.generateMatrix(3));
        // System.out.println(solution.summaryRanges(new int[] { 1, 2, 3, 4, 6, 7, 8, 11 }));
        System.out.println(solution.findMissingRanges(new int[] { 1, 1, 1 }, 1, 1));
        // int[] array = new int[] { 2, 2, 3, 4, };
        // System.out.println(solution.triangleNumber(array));
        solution.nextPermutation(new int[] { 1, 2 });
    }
}
