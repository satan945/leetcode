/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 267. Palindrome Permutation II
 * <p>
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no
 * palindromic permutation could be form.
 * 
 * For example:
 * 
 * Given s = "aabb", return ["abba", "baab"].
 * 
 * Given s = "abc", return [].
 * 
 * </p>
 * 
 * @author abel created on 2018/1/3 下午2:46
 * @version $Id$
 */
public class PalindromePermutationII {

    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        int oddCount = 0;
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            countMap.put(ch, countMap.getOrDefault(s.charAt(i), 0) + 1);
            oddCount += countMap.get(ch) % 2 == 0 ? -1 : 1;
        }
        if (oddCount > 1) {
            return res;
        }
        List<Character> keys = new ArrayList<>();
        String mid = "";
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            int num = entry.getValue();
            for (int i = 0; i < num / 2; i++) {
                keys.add(entry.getKey());
            }
            if (num % 2 == 1) {
                mid += entry.getKey();
            }
        }
        helper(res, mid, keys, new StringBuilder(), new boolean[keys.size()]);
        return res;
    }

    private void helper(List<String> res, String mid, List<Character> keys, StringBuilder sb, boolean[] used) {
        if (sb.length() == keys.size()) {
            String val = sb.toString() + mid + sb.reverse().toString();
            res.add(val);
            sb.reverse();
            return;
        }
        for (int i = 0; i < keys.size(); i++) {
            if (i > 0 && keys.get(i) == keys.get(i - 1) && !used[i-1]) {
                continue;
            }
            if (!used[i]) {
                used[i] = true;
                sb.append(keys.get(i));
                helper(res, mid, keys, sb, used);
                sb.deleteCharAt(sb.length() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        PalindromePermutationII solution = new PalindromePermutationII();
        System.out.println(solution.generatePalindromes("aaaa"));
    }
}
