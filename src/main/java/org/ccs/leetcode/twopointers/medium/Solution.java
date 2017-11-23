/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.twopointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
        return matchPermutation(s1Map, s2Map);

    }

    private boolean matchPermutation(int[] s1Map, int[] s2Map) {
        for (int i = 0; i < 26; i++) {
            if (s1Map[i] != s2Map[i]) {
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

    /**
     * 360. Sort Transformed Array
     * <p>
     * https://leetcode.com/problems/sort-transformed-array
     * <p>
     * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx
     * + c to each element x in the array.
     * 
     * The returned array must be in sorted order.
     * 
     * Expected time complexity: O(n)
     * 
     * Example: nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
     * 
     * Result: [3, 9, 15, 33]
     * 
     * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
     * 
     * Result: [-23, -5, 1, 7]
     * </p>
     * 
     * @param nums
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length];
        int i = 0, j = nums.length - 1;
        int index = a >= 0 ? nums.length - 1 : 0;
        while (i <= j) {
            int numLeft = quad(nums[i], a, b, c);
            int numRight = quad(nums[j], a, b, c);
            if (a >= 0) {
                res[index--] = numLeft >= numRight ? numLeft : numRight;
                if (numLeft >= numRight) {
                    i++;
                } else {
                    j--;
                }
            } else {
                res[index++] = numLeft >= numRight ? numRight : numLeft;
                if (numLeft >= numRight) {
                    j--;
                } else {
                    i++;
                }
            }
            if (numLeft >= numRight) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    /**
     * 524. Longest Word in Dictionary through Deleting
     * <p>
     * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting
     * <p>
     * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting
     * some characters of the given string. If there are more than one possible results, return the longest word with
     * the smallest lexicographical order. If there is no possible result, return the empty string.
     * 
     * Example 1: Input: s = "abpcplea", d = ["ale","apple","monkey","plea"]
     * 
     * Output: "apple" Example 2: Input: s = "abpcplea", d = ["a","b","c"]
     * 
     * Output: "a" Note: All the strings in the input will only contain lower-case letters. The size of the dictionary
     * won't exceed 1,000. The length of all the strings in the input won't exceed 1,000.
     * </p>
     * https://discuss.leetcode.com/topic/80799/short-java-solutions-sorting-dictionary-and-without-sorting/2
     * 
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        d.sort((a, b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) : a.compareTo(b));
        for (String str : d) {
            int i = 0;
            char[] array = s.toCharArray();
            for (char c : array) {
                if (i < str.length() && c == str.charAt(i)) {
                    i++;
                }
                if (i == str.length()) {
                    return str;
                }
            }
        }
        return "";
    }

    /**
     * 713. Subarray Product Less Than K
     * <p>
     * Your are given an array of positive integers nums.
     * 
     * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is
     * less than k.
     * 
     * Example 1: Input: nums = [10, 5, 2, 6], k = 100 Output: 8
     * 
     * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6],
     * [5, 2, 6]. Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
     *
     * Note:
     * 
     * 0 < nums.length <= 50000. 0 < nums[i] < 1000. 0 <= k < 10^6.
     * </p>
     * 
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (j == i) {
                    product *= nums[i];
                } else {
                    product *= nums[j];
                }
                System.out.println(product);
                if (product < k) {
                    res++;
                } else {
                    product = 1;
                    break;
                }
                if (j == nums.length - 1) {
                    product = 1;
                }
            }
        }
        return res;
    }

    /**
     * 395. Longest Substring with At Least K Repeating Characters
     * <p>
     * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters
     * <p>
     * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every
     * character in T appears no less than k times.
     * 
     * Example 1:
     * 
     * Input: s = "aaabb", k = 3
     * 
     * Output: 3
     * 
     * The longest substring is "aaa", as 'a' is repeated 3 times. Example 2:
     * 
     * Input: s = "ababbc", k = 2
     * 
     * Output: 5
     * 
     * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
     * </p>
     * todo
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] charCounts = new int[26];
        return 0;

    }

    private int quad(int num, int a, int b, int c) {
        return a * num * num + b * num + c;
    }

    public static void main(String[] args) {
        int[] colors = new int[] { 0, 1 };
        Solution solution = new Solution();
        System.out.println(solution.numSubarrayProductLessThanK(new int[] { 10, 5, 2, 6 }, 100));
    }
}
