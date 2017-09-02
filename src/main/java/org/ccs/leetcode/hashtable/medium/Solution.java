/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.hashtable.medium;

import java.util.ArrayList;
import java.util.HashMap;
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
}
