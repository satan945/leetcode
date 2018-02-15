/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.interview.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 772. Group Anagrams
 * 
 * http://www.lintcode.com/en/problem/group-anagrams/
 * 
 * @author Abel created on 2018/2/12 22:38
 * @version $Id$
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Arrays.sort(strs, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            } else {
                return o1.length() - o2.length();
            }
        });
        Set<String> used = new HashSet<>();
        int i = 0;
        while (i < strs.length) {
            List<String> list = new ArrayList<>();
            if (!used.add(strs[i])) {
                i++;
                continue;
            }
            list.add(strs[i]);
            int[] map = getMap(strs[i]);
            for (int j = i + 1; j < strs.length; j++) {
                if (strs[i].length() != strs[j].length()) {
                    break;
                }
                if (isAnagram(strs[j], map)) {
                    list.add(strs[j]);
                    used.add(strs[j]);
                }
            }
            res.add(list);
        }
        // write your code here
        return res;
    }

    private int[] getMap(String str) {
        int[] map = new int[26];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i) - 'a']++;
        }
        return map;
    }

    private boolean isAnagram(String str, int[] map) {
        int[] map2 = getMap(str);
        for (int i = 0; i < 26; i++) {
            if (map[i] != map2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        System.out.println(solution.groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));
    }
}
