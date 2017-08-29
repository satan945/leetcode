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
}
