/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 15. 3Sum
     * <p>
     * https://leetcode.com/problems/3sum
     * <p>
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets
     * in the array which gives the sum of zero.
     * 
     * Note: The solution set must not contain duplicate triplets.
     * 
     * For example, given array S = [-1, 0, 1, 2, -1, -4],
     * 
     * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i + 2 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            int target = -nums[i];
            while (k > j) {
                if (nums[j] + nums[k] == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    /**
     * 16. 3Sum Closest
     * <p>
     * https://leetcode.com/problems/3sum-closest
     * <p>
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
     * Return the sum of the three integers. You may assume that each input would have exactly one solution.
     * 
     * For example, given array S = {-1 2 1 -4}, and target = 1.
     * 
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     * </p>
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i + 2 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int remain = target - nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == remain) {
                    return target;
                } else {
                    int curDiff = Math.abs(remain - (nums[j] + nums[k]));
                    if (curDiff < diff) {
                        diff = curDiff;
                        result = nums[i] + nums[j] + nums[k];
                    }
                    if (nums[j] + nums[k] > remain) {
                        k--;
                        while (j < k && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    } else {
                        j++;
                        while (j < k && nums[j] == nums[j - 1]) {
                            j++;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 
     * @param num
     * @param target
     * @return
     */
    public int threeSumClosestSimple(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum == target) {
                    return target;
                }
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = { { 1, 2, }, { 3, 4 } };
        // solution.rotate(matrix);
        int[] array = new int[] { 1, 1, 1, 0 };
        System.out.println(solution.majorityElement(array));
    }
}
