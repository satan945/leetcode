/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.hashtable.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author abel created on 2017/8/29 下午4:43
 * @version $Id$
 */
public class Solution {

    /**
     * 599. Minimum Index Sum of Two Lists
     * <p>
     * https://leetcode.com/problems/minimum-index-sum-of-two-lists
     * <p>
     * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants
     * represented by strings.
     *
     * You need to help them find out their common interest with the least list index sum. If there is a choice tie
     * between answers, output all of them with no order requirement. You could assume there always exists an answer.
     *
     * Example 1:
     *
     * Input: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter
     * Steakhouse", "Shogun"]
     *
     * Output: ["Shogun"]
     *
     * Explanation: The only restaurant they both like is "Shogun".
     *
     * Example 2:
     *
     * Input: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["KFC", "Shogun", "Burger King"]
     *
     * Output: ["Shogun"]
     *
     * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1). Note:
     * The length of both lists will be in the range of [1, 1000]. The length of strings in both lists will be in the
     * range of [1, 30]. The index is starting from 0 to the list length minus 1. No duplicates in both lists.
     * </p>
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            String name = list1[i];
            map.put(name, i);
        }
        int minSum = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            String name = list2[i];
            if (map.containsKey(name)) {
                int count = map.get(name);
                if (count + i < minSum) {
                    res.clear();
                    minSum = count + i;
                }
                if (count + i <= minSum) {
                    res.add(name);
                }
            }

        }
        return res.toArray(new String[res.size()]);
    }

    /**
     * 463. Island Perimeter
     * <p>
     * https://leetcode.com/problems/island-perimeter
     * <p>
     * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
     * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
     * and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water
     * inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is
     * rectangular, width and height don't exceed 100. Determine the perimeter of the island.
     * 
     * Example:
     * 
     * [[0,1,0,0], [1,1,1,0], [0,1,0,0], [1,1,0,0]]
     * 
     * Answer: 16
     *
     * Explanation: The perimeter is the 16 yellow stripes in the image below:
     * </p>
     * 
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int islandCount = 0;
        int neighborCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    islandCount++;
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        neighborCount++;
                    }
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        neighborCount++;
                    }
                }
            }
        }
        return islandCount * 4 - neighborCount * 2;
    }

    /**
     * 594. Longest Harmonious Subsequence
     * <p>
     * https://leetcode.com/problems/longest-harmonious-subsequence
     * <p>
     * We define a harmonious array is an array where the difference between its maximum value and its minimum value is
     * exactly 1.
     * 
     * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its
     * possible subsequences.
     * 
     * Example 1:
     * 
     * Input: [1,3,2,2,5,2,3,7]
     * 
     * Output: 5
     * 
     * Explanation: The longest harmonious subsequence is [3,2,2,2,3]. Note: The length of the input array will not
     * exceed 20,000.
     * </p>
     * 
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        int prevCount = 1;
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            if (i > 0 && nums[i] - 1 == nums[i - 1]) {
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                res = Math.max(res, count + prevCount);
                prevCount = count;
            } else {
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                prevCount = count;
            }
        }
        return res;
    }

    public int findLHSHashMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num) + map.get(num + 1));
            }
        }
        return res;
    }

    public int findLHSHashMapOneLoop(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num) + map.get(num + 1));
            }
            if (map.containsKey(num - 1)) {
                res = Math.max(res, map.get(num - 1) + map.get(num));
            }
        }
        return res;
    }

    /**
     * 447. Number of Boomerangs
     * <p>
     * https://leetcode.com/problems/number-of-boomerangs
     * <p>
     * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such
     * that the distance between i and j equals the distance between i and k (the order of the tuple matters).
     * 
     * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the
     * range [-10000, 10000] (inclusive).
     * 
     * Example: Input: [[0,0],[1,0],[2,0]]
     * 
     * Output: 2
     * 
     * Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
     * </p>
     * todo
     * 
     * @param points
     * @return
     * 
     */
    public int numberOfBoomerangs(int[][] points) {
        return 0;
    }

    /**
     * 205. Isomorphic Strings
     * <p>
     * https://leetcode.com/problems/isomorphic-strings
     * <p>
     * Given two strings s and t, determine if they are isomorphic.
     * 
     * Two strings are isomorphic if the characters in s can be replaced to get t.
     * 
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character but a character may map to itself.
     * 
     * For example, Given "egg", "add", return true.
     * 
     * Given "foo", "bar", return false.
     * 
     * Given "paper", "title", return true.
     * 
     * Note: You may assume both s and t have the same length.
     * </p>
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            char val = t.charAt(i);
            Character existVal = map.get(key);
            if (existVal != null && existVal != val) {
                return false;
            }
            if (existVal == null && map.values().contains(val)) {
                return false;
            }
            map.putIfAbsent(key, val);
        }
        return true;
    }

    public boolean isIsomorphicWithoutMap(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i) + 256])
                return false;
            m[s1.charAt(i)] = m[s2.charAt(i) + 256] = i + 1;
        }
        return true;
    }

    /**
     * 290. Word Pattern
     * <p>
     * https://leetcode.com/problems/word-pattern
     * <p>
     * Given a pattern and a string str, find if str follows the same pattern.
     * 
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word
     * in str.
     * 
     * Examples: pattern = "abba", str = "dog cat cat dog" should return true. pattern = "abba", str = "dog cat cat
     * fish" should return false. pattern = "aaaa", str = "dog cat cat dog" should return false. pattern = "abba", str =
     * "dog dog dog dog" should return false. Notes: You may assume pattern contains only lowercase letters, and str
     * contains lowercase letters separated by a single space.
     * 
     * </p>
     * 
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        if (strs.length != pattern.length()) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            String val = strs[i];
            String existStr = map.get(key);
            if (existStr != null && !existStr.equals(val)) {
                return false;
            }
            if (existStr == null && map.values().contains(val)) {
                return false;
            }
            map.putIfAbsent(key, val);
        }
        return true;
    }

    /**
     * 266. Palindrome Permutation
     * <p>
     * https://leetcode.com/problems/palindrome-permutation
     * <p>
     * Given a string, determine if a permutation of the string could form a palindrome.
     * 
     * For example, "code" -> False, "aab" -> True, "carerac" -> True.
     * </p>
     * 
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (count > 1) {
                return false;
            }
            if (entry.getValue() % 2 == 1) {
                count++;
            }
        }
        return count <= 1;
    }

    /**
     * 438. Find All Anagrams in a String
     * <p>
     * https://leetcode.com/problems/find-all-anagrams-in-a-string
     * <p>
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
     * 
     * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than
     * 20,100.
     * 
     * The order of output does not matter.
     * 
     * Example 1:
     * 
     * Input: s: "cbaebabacd" p: "abc"
     * 
     * Output: [0, 6]
     * 
     * Explanation:
     * 
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * 
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     * 
     * Example 2:
     * 
     * Input: s: "abab" p: "ab"
     * 
     * Output: [0, 1, 2]
     * 
     * Explanation:
     * 
     * The substring with start index = 0 is "ab", which is an anagram of "ab".
     * 
     * The substring with start index = 1 is "ba", which is an anagram of "ab".
     * 
     * The substring with start index = 2 is "ab", which is an anagram of "ab".
     * </p>
     * 
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] sMap = new int[26];
        int[] pMap = new int[26];
        for (int i = 0; i < p.length(); i++) {
            sMap[s.charAt(i) - 'a']++;
            pMap[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length() - p.length(); i++) {
            if (matchAnagram(sMap, pMap)) {
                res.add(i);
            }
            sMap[s.charAt(i + p.length()) - 'a']++;
            pMap[s.charAt(i) - 'a']--;
        }
        if (matchAnagram(sMap, pMap)) {
            res.add(s.length() - p.length() + 1);
        }
        return res;
    }

    private boolean matchAnagram(int[] sMap, int[] pMap) {
        for (int i = 0; i < 26; i++) {
            if (sMap[i] != pMap[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // System.out.println(new Solution().findLHS(new int[] { 1, 3, 2, 2, 5, 2, 3, 7 }));
        System.out.println(new Solution().isIsomorphic("ab", "aa"));
    }
}
