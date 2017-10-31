/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.hashtable.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * todo
     * 
     * @param paths
     * @return
     */
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
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
}
