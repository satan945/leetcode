/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.hashtable.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author abel created on 2017/8/29 下午4:43
 * @version $Id$
 */
public class Solution {
    /**
     * 249. Group Shifted Strings
     * <p>
     * https://leetcode.com/problems/group-shifted-strings
     * <p>
     * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can
     * keep "shifting" which forms the sequence:
     *
     * "abc" -> "bcd" -> ... -> "xyz" Given a list of strings which contains only lowercase alphabets, group all strings
     * that belong to the same shifting sequence.
     *
     * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], A solution is:
     *
     * [ ["abc","bcd","xyz"], ["az","ba"], ["acef"], ["a","z"] ]
     * </p>
     *
     * @param strings
     * @return
     */
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length() - 1; i++) {
                char r = str.charAt(i + 1);
                char l = str.charAt(i);
                int sub = r - l;
                if (sub < 0) {
                    sub += 26;
                }
                sb.append(sb.length() > 0 ? "+" : "").append(sub);
            }
            List<String> list = map.computeIfAbsent(sb.toString(), k -> new ArrayList<>());
            list.add(str);
        }
        res.addAll(map.values());
        return res;
    }

    /**
     * 347. Top K Frequent Elements
     * <p>
     * https://leetcode.com/problems/top-k-frequent-elements
     * <p>
     * Given a non-empty array of integers, return the k most frequent elements.
     * 
     * For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
     * 
     * Note: You may assume k is always valid, 1 ≤ k ≤ number of unique elements. Your algorithm's time complexity must
     * be better than O(n log n), where n is the array's size.
     * 
     * </p>
     * 
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) {
            return res;
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            Integer count = countMap.get(num);
            countMap.put(num, count == null ? 1 : ++count);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (buckets[entry.getValue()] == null) {
                buckets[entry.getValue()] = new ArrayList<>();
            }
            buckets[entry.getValue()].add(entry.getKey());
        }
        for (int i = buckets.length - 1; i >= 0 && res.size() < k; i--) {
            if (buckets[i] != null) {
                res.addAll(buckets[i]);
            }
        }
        return res;
    }

    /**
     * 36. Valid Sudoku
     * <p>
     * https://leetcode.com/problems/valid-sudoku
     * <p>
     * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
     * 
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     * </p>
     * 
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> row = new HashSet<>();
            HashSet<Character> column = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !row.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !column.add(board[j][i])) {
                    return false;
                }
                int cubeRowIndex = (3 * (i / 3)) + j / 3;
                int cubeColIndex = (3 * (i % 3)) + j % 3;
                if (board[cubeRowIndex][cubeColIndex] != '.' && !cube.add(board[cubeRowIndex][cubeColIndex])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 609. Find Duplicate File in System
     * <p>
     * https://leetcode.com/problems/find-duplicate-file-in-system
     * <p>
     * Given a list of directory info including directory path, and all the files with contents in this directory, you
     * need to find out all the groups of duplicate files in the file system in terms of their paths.
     * 
     * A group of duplicate files consists of at least two files that have exactly the same content.
     * 
     * A single directory info string in the input list has the following format:
     * 
     * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
     * 
     * It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content,
     * respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is
     * just the root directory.
     * 
     * The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the
     * files that have the same content. A file path is a string that has the following format:
     * 
     * "directory_path/file_name.txt"
     * 
     * Example 1: Input: ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root
     * 4.txt(efgh)"] Output: [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]] Note: No
     * order is required for the final output. You may assume the directory name, file name and file content only has
     * letters and digits, and the length of file content is in the range of [1,50]. The number of files given is in the
     * range of [1,20000]. You may assume no files or directories share the same name in the same directory. You may
     * assume each given directory info represents a unique directory. Directory path and file info are separated by a
     * single blank space. Follow-up beyond contest: Imagine you are given a real file system, how will you search
     * files? DFS or BFS? If the file content is very large (GB level), how will you modify your solution? If you can
     * only read the file by 1kb each time, how will you modify your solution? What is the time complexity of your
     * modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize? How to
     * make sure the duplicated files you find are not false positive?
     * </p>
     * 
     * @param paths
     * @return
     */
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] strs = path.split(" ");
            String dir = strs[0];
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                String name = str.substring(0, str.indexOf("("));
                String content = str.substring(str.indexOf("("), str.indexOf(")"));
                map.computeIfAbsent(content, k -> new ArrayList<>());
                map.get(content).add(dir + "/" + name);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                res.add(entry.getValue());
            }
        }
        return res;
    }

    /**
     * 692. Top K Frequent Words
     * <p>
     * https://leetcode.com/problems/top-k-frequent-words
     * <p>
     * Given a non-empty list of words, return the k most frequent elements.
     * 
     * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the
     * word with the lower alphabetical order comes first.
     * </p>
     * 
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String str : words) {
            int frequency = frequencyMap.getOrDefault(str, 0) + 1;
            frequencyMap.put(str, frequency);
        }
        List<Integer> freqList = new ArrayList<>();
        Map<Integer, List<String>> strMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            freqList.add(entry.getValue());
            List<String> list = strMap.get(entry.getValue());
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(entry.getKey());
            strMap.put(entry.getValue(), list);
        }
        freqList.sort((o1, o2) -> o2 - o1);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k;) {
            List<String> strs = strMap.get(freqList.get(i));
            int size = strs.size();
            strs.sort(Comparator.naturalOrder());
            if (res.size() + size <= k) {
                res.addAll(strs);
            } else {
                res.addAll(strs.subList(0, k - res.size()));
            }
            i += strs.size();
        }
        return res;
    }

    /**
     * 454. 4Sum II
     * <p>
     * https://leetcode.com/problems/4sum-ii
     * <p>
     * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] +
     * B[j] + C[k] + D[l] is zero.
     * 
     * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the
     * range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
     * 
     * Example:
     * 
     * Input:
     * 
     * A = [ 1, 2] B = [-2,-1] C = [-1, 2] D = [ 0, 2]
     * 
     * Output: 2
     * 
     * Explanation: The two tuples are: 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0 2. (1, 1,
     * 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     * </p>
     * 
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = A.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = C[i] + D[j];
                res += map.getOrDefault(-sum, 0);
            }
        }
        return res;
    }

    /**
     * 18. 4Sum
     * <p>
     * https://leetcode.com/problems/4sum
     * <p>
     * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all
     * unique quadruplets in the array which gives the sum of target.
     * 
     * Note: The solution set must not contain duplicate quadruplets.
     * 
     * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
     * 
     * A solution set is: [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
     * </p>
     * 
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length <= 3) {
            return new ArrayList<>();
        }
        int n = nums.length;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            threeSum(nums, target - nums[i], i + 1, n - 1, list, res);
        }
        return res;
    }

    private void threeSum(int[] nums, int target, int begin, int end, List<Integer> list, List<List<Integer>> res) {
        for (int i = begin; i <= end; i++) {
            if (i > begin && nums[i] == nums[i - 1]) {
                continue;
            }
            List<Integer> list2 = new ArrayList<>(list);
            list2.add(nums[i]);
            twoSum(nums, target - nums[i], i + 1, end, list2, res);
        }
    }

    private void twoSum(int[] nums, int target, int begin, int end, List<Integer> list2, List<List<Integer>> res) {
        if (begin >= end) {
            return;
        }
        int l = begin;
        int r = end;
        while (l < r) {
            List<Integer> list = new ArrayList<>(list2);
            int sum = nums[l] + nums[r];
            if (target - sum == 0) {
                list.add(nums[l]);
                list.add(nums[r]);
                res.add(list);
                int x = nums[l];
                while (++l < r && x == nums[l])
                    ;
                x = nums[r];
                while (--r > l && x == nums[r])
                    ;
            } else if (target - sum > 0) {
                l++;
            } else {
                r--;
            }
        }

    }

    /**
     * 274. H-Index
     * <p>
     * https://leetcode.com/problems/h-index
     * <p>
     * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to
     * compute the researcher's h-index.
     * 
     * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at
     * least h citations each, and the other N − h papers have no more than h citations each."
     * 
     * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them
     * had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations
     * each and the remaining two with no more than 3 citations each, his h-index is 3.
     * 
     * Note: If there are several possible values for h, the maximum one is taken as the h-index.
     * </p>
     * 
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        while (i < citations.length && citations[citations.length - i - 1] > i) {
            i++;
        }
        return i;
    }

    /**
     * 325. Maximum Size Subarray Sum Equals k
     * <p>
     * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k
     * <p>
     * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't
     * one, return 0 instead.
     * 
     * Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
     * 
     * Example 1: Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is
     * the longest)
     * 
     * Example 2: Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the subarray [-1, 2] sums to 1 and is the
     * longest)
     * 
     * Follow Up: Can you do it in O(n) time?
     * </p>
     * 
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                res = i + 1;
            } else if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { -3, -2, -1, 0, 0, 1, 2, 3 };
        System.out.println(solution.fourSum(nums, 0));
    }

}
