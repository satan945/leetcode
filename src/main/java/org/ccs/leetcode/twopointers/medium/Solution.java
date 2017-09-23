/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.twopointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author abel created on 2017/8/10 下午10:23
 * @version $Id$
 */
public class Solution {

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
     * 
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            count += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return count;
    }

    public int twoSumSmaller(int[] nums, int start, int target) {
        int count = 0;
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    /**
     * <p>
     * https://leetcode.com/problems/max-consecutive-ones-ii
     * <p>
     * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
     * 
     * Example 1:
     *
     * Input: [1,0,1,1,0]
     *
     * Output: 4
     *
     * Explanation: Flip the first zero will get the the maximum number of consecutive 1s. After flipping, the maximum
     * number of consecutive 1s is 4.
     *
     * Note:
     * 
     * The input array will only contain 0 and 1. The length of input array is a positive integer and will not exceed
     * 10,000 Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't
     * store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     *
     *
     * Follow up:
     * 
     * What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers
     * coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     * </p>
     * 
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        return 0;
    }

    /**
     * 75. Sort Colors
     * <p>
     * https://leetcode.com/problems/sort-colors
     * <p>
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are
     * adjacent, with the colors in the order red, white and blue.
     * 
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * 
     * Note: You are not suppose to use the library's sort function for this problem.
     * </p>
     * 
     * @param nums
     */
    public void sortColors(int[] nums) {
        int zero = 0, two = nums.length - 1;
        for (int i = 0; i <= two; i++) {
            while (nums[i] == 2 && i < two) {
                swap(nums, i, two);
                two--;
            }
            while (nums[i] == 0 && i > zero) {
                swap(nums, i, zero);
                zero++;
            }
        }

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors1(int[] nums) {
        int[] counts = new int[3];
        for (int num : nums) {
            counts[num]++;
        }
        int i = 0;
        for (int j = 0; j < counts.length; j++) {
            int count = counts[j];
            while (count > 0 && i < nums.length) {
                nums[i] = j;
                count--;
                i++;
            }
        }
    }

    /**
     * 567. Permutation in String
     * <p>
     * https://leetcode.com/problems/permutation-in-string
     * <p>
     * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other
     * words, one of the first string's permutations is the substring of the second string.
     * 
     * Example 1:
     * 
     * Input:s1 = "ab" s2 = "eidbaooo" Output:True
     * 
     * Explanation: s2 contains one permutation of s1 ("ba").
     * 
     * Example 2: Input:s1= "ab" s2 = "eidboaoo"
     * 
     * Output: False
     * 
     * Note:
     * 
     * The input strings only contain lower case letters. The length of both given strings is in range [1, 10,000].
     * </p>
     * 
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1Map = new int[26];
        int[] s2Map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1Map[s1.charAt(i) - 'a']++;
            s2Map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matchPermutation(s1Map, s2Map)) {
                return true;
            }
            s2Map[s2.charAt(i + s1.length()) - 'a']++;
            s2Map[s2.charAt(i) - 'a']--;
        }
        return matchPermutation(s1Map,s2Map);

    }

    private boolean matchPermutation(int[] s1Map, int[] s2Map) {
        for(int i=0;i<26;i++){
            if(s1Map[i]!=s2Map[i]){
                return false;
            }
        }
        return true;
    }

    private boolean isPermutation(String s1, String s2) {
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] colors = new int[] { 0, 1 };
        new Solution().sortColors1(colors);
    }
}
