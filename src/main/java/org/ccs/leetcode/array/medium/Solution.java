/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ccs.leetcode.bean.Interval;

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
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand e
     * 
     * 1,2,3 → 1,3,2
     * 
     * 3,2,1 → 1,2,3
     * 
     * 1,1,5 → 1,5,1
     * </p>
     * http://blog.csdn.net/qq575787460/article/details/41215475
     * 
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int len = nums.length;
        int i = len - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = len - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, len - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 287. Find the Duplicate Number
     * <p>
     * https://leetcode.com/problems/find-the-duplicate-number
     * <p>
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at
     * least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     * 
     * Note: You must not modify the array (assume the array is read only). You must use only constant, O(1) extra
     * space. Your runtime complexity should be less than O(n2). There is only one duplicate number in the array, but it
     * could be repeated more than once.
     * </p>
     * https://segmentfault.com/a/1190000003817671
     * 
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int low = 1;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
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
     * https://discuss.leetcode.com/topic/29054/easiest-java-solution-with-explanation
     * 
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int neighbor = calLiveNeighbors(board, i, j, m, n);
                if (board[i][j] == 0 && neighbor == 3) {
                    board[i][j] = 5;
                }
                if (board[i][j] == 1) {
                    if (neighbor < 2) {
                        board[i][j] = 2;
                    } else if (neighbor == 2 || neighbor == 3) {
                        board[i][j] = 3;
                    } else {
                        board[i][j] = 4;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (board[i][j]) {
                case 0:
                    break;
                case 2:
                case 4:
                    board[i][j] = 0;
                    break;
                case 3:
                    board[i][j] = 1;
                    break;
                case 5:
                    board[i][j] = 1;
                    break;
                default:
                    break;
                }
            }
        }
    }

    private int calLiveNeighbors(int[][] board, int i, int j, int m, int n) {
        int res = 0;
        int[][] moves = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };
        for (int[] move : moves) {
            if (i + move[0] >= m || i + move[0] < 0 || j + move[1] >= n || j + move[1] < 0) {
                continue;
            }
            int neiVal = board[i + move[0]][j + move[1]];
            if (neiVal == 1 || neiVal == 3 || neiVal == 2 || neiVal == 4) {
                res++;
            }
        }

        return res;
    }

    /**
     * 80. Remove Duplicates from Sorted Array II
     * <p>
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii
     * <p>
     * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
     *
     * For example, Given sorted array nums = [1,1,1,2,2,3],
     *
     * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't
     * matter what you leave beyond the new length.
     * </p>
     * 
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int repeat = 2;
        if (nums.length <= 2) {
            return nums.length;
        }
        int i = 1, j = 1;
        int count = 1;
        while (j < nums.length) {
            if (nums[j] != nums[j - 1]) {
                count = 1;
                nums[i] = nums[j];
                i++;
            } else {
                if (count < repeat) {
                    nums[i] = nums[j];
                    i++;
                    count++;
                }
            }
            j++;
        }
        return i;
    }

    /**
     * 215. Kth Largest Element in an Array
     * <p>
     * https://leetcode.com/problems/kth-largest-element-in-an-array
     * <p>
     * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
     * not the kth distinct element.
     *
     * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
     *
     * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
     * </p>
     * https://discuss.leetcode.com/topic/14597/solution-explained
     *
     * @param nums
     * @param k
     * @return
     *
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 540. Single Element in a Sorted Array
     * <p>
     * https://leetcode.com/problems/single-element-in-a-sorted-array
     * <p>
     * Given a sorted array consisting of only integers where every element appears twice except for one element which
     * appears once. Find this single element that appears only once.
     * 
     * Example 1: Input: [1,1,2,3,3,4,4,8,8] Output: 2 Example 2: Input: [3,3,7,7,10,11,11] Output: 10 Note: Your
     * solution should run in O(log n) time and O(1) space.
     * </p>
     * 
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        if (nums[0] != nums[1]) {
            return nums[0];
        }
        for (int i = 1; i < nums.length - 2; i++) {
            if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * 162. Find Peak Element
     * <p>
     * https://leetcode.com/problems/find-peak-element
     * <p>
     * A peak element is an element that is greater than its neighbors.
     * 
     * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
     * 
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     * 
     * You may imagine that num[-1] = num[n] = -∞.
     * 
     * </p>
     * 
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    /**
     * 697. Degree of an Array
     * <p>
     * https://leetcode.com/problems/degree-of-an-array
     * <p>
     * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum
     * frequency of any one of its elements.
     * 
     * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as
     * nums.
     * 
     * Example 1:
     * 
     * Input: [1, 2, 2, 3, 1]
     * 
     * Output: 2
     * 
     * Explanation: The input array has a degree of 2 because both elements 1 and 2 appear twice. Of the subarrays that
     * have the same degree: [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2] The shortest
     * length is 2. So return 2.
     * 
     * Example 2: Input: [1,2,2,3,1,4,2]
     * 
     * Output: 6
     * 
     * Note:
     * 
     * nums.length will be between 1 and 50,000. nums[i] will be an integer between 0 and 49,999.
     * </p>
     * 
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int degree = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer[]> rangeMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
            degree = Math.max(degree, countMap.get(nums[i]));
            Integer[] range;
            if (!rangeMap.containsKey(nums[i])) {
                rangeMap.put(nums[i], new Integer[] { i, i });
            } else {
                range = rangeMap.get(nums[i]);
                range[1] = i;
            }
        }

        int minRange = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == degree) {
                Integer[] range = rangeMap.get(entry.getKey());
                minRange = Math.min(minRange, (range[1] - range[0] + 1));
            }
        }
        return minRange;
    }

    /**
     * 442. Find All Duplicates in an Array
     * <p>
     * https://leetcode.com/problems/find-all-duplicates-in-an-array
     * <p>
     * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
     * 
     * Find all the elements that appear twice in this array.
     * 
     * Could you do it without extra space and in O(n) runtime?
     * 
     * Example:
     * 
     * Input: [4,3,2,7,8,2,3,1]
     * 
     * Output: [2,3]
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int location = Math.abs(nums[i]) - 1;
            if (nums[location] < 0) {
                res.add(location+1);
            } else {
                nums[location] = -nums[location];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] { 3, 2, 4 };
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        // solution.rotate(matrix);
        // System.out.println(solution.spiralOrder(matrix));
        // System.out.println(solution.generateMatrix(3));
        // System.out.println(solution.summaryRanges(new int[] { 1, 2, 3, 4, 6, 7, 8, 11 }));
        // System.out.println(solution.findMissingRanges(new int[] { 1, 1, 1 }, 1, 1));
        // int[] array = new int[] { 2, 2, 3, 4, };
        // System.out.println(solution.triangleNumber(array));
        solution.nextPermutation(new int[] { 1, 2 });
        int[] array2 = new int[] { 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88,
                88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 74, 80, 15, 39, 9, 79, 57, 94, 71, 63, 13, 16, 55, 79,
                62, 84, 81, 74, 85, 29, 27, 59, 52, 37, 11, 26, 91, 41, 75, 80, 20, 3, 38, 59, 9, 13, 55, 11, 85, 52,
                30, 72, 61, 81, 88, 81, 89, 54, 32, 23, 6, 15, 68, 61, 87, 40, 8, 96, 2, 51, 44, 64, 54, 79, 39, 61, 73,
                87, 10, 59, 50, 58, 100, 48, 76, 27, 55, 41, 22, 45, 62, 3, 10, 9, 12, 97, 71, 31, 70, 37, 26, 95, 16,
                24, 43, 87, 91, 90, 89, 31, 10, 46, 5, 49, 67, 28, 51, 16, 65, 6, 85, 35, 34, 56, 19, 100, 44, 84, 77,
                10, 81, 16, 59, 9, 39, 99, 3, 25, 29, 86, 88, 5, 50, 55, 15, 81, 52, 68, 39, 64, 72, 66, 90, 50, 18, 0,
                4, 99, 38, 28, 60, 77, 71, 12, 9, 92, 86, 58, 72, 9, 12, 100, 0, 28, 39, 1, 25, 98, 38, 4, 48, 21, 2,
                15, 1, 16, 51, 37, 69, 50, 65, 45, 16, 25, 88, 30, 6, 25, 28, 73, 57, 80, 69, 87, 83, 26, 79, 58, 98,
                42, 83, 51, 44, 8, 53, 27, 29, 61, 14, 6, 39, 21, 54, 56, 67, 53, 26, 50, 84, 32, 27, 76, 93, 7, 74, 82,
                32, 10, 18, 47, 74, 56, 60, 74, 32, 46, 86, 14, 69, 61, 48, 88, 84, 56, 76, 4, 91, 0, 72, 80, 63, 47,
                75, 27, 58, 35, 100, 94, 72, 58, 65, 78, 77, 74, 14, 22, 8, 75, 48, 32, 97, 86, 81, 88, 29, 83, 65, 72,
                61, 52, 45, 8, 50, 34, 33, 37, 44, 0, 1, 59, 51, 13, 18, 3, 25, 85, 36, 8, 5, 69, 32, 98, 35, 68, 87,
                21, 6, 51, 15, 66, 81, 92, 95, 5, 4, 60, 100, 56, 5, 97, 21, 47, 55, 72, 15, 63, 47, 21, 53, 62, 81, 46,
                16, 67, 10, 21, 67, 94, 27, 54, 77, 58, 91, 78, 23, 61, 62, 93, 51, 45, 50, 39, 17, 75, 78, 41, 12, 32,
                12, 86, 2, 37, 23, 7, 68, 10, 56, 92, 32, 24, 71, 44, 36, 11, 64, 64, 90, 80, 75, 99, 96, 13, 44, 5,
                100, 85, 45, 31, 2, 89, 12, 18, 4, 7, 75, 96, 79, 45, 87, 48, 8, 0, 65, 3, 25, 35, 49, 72, 93, 54, 81,
                65, 77, 49, 13, 30, 61, 33, 7, 10, 19, 83, 75, 6, 48, 32, 79, 87, 92, 73, 31, 41, 32, 26, 93, 53, 24,
                86, 47, 99, 20, 31, 84, 3, 1, 51, 46, 37, 80, 94, 22, 19, 93, 15, 81, 72, 48, 81, 4, 61, 78, 90, 100,
                85, 3, 37, 41, 55, 61, 18, 89, 88, 33, 92, 83, 92, 24, 19, 36, 61, 99, 91, 37, 78, 78, 85, 95, 63, 71,
                72, 16, 53, 2, 66, 95, 28, 17, 47, 56 };
        System.out.println(solution.findShortestSubArray(array2));
        System.out.println(solution.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2));
    }
}
