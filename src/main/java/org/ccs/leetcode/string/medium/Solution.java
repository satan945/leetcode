/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.string.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author Abel created on 2017/7/7 11:39
 * @version $Id$
 */
public class Solution {

    /**
     * 8. String to Integer (atoi)
     * <p>
     * https://leetcode.com/problems/string-to-integer-atoi/#/description
     * 
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }

        return 0;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null) {
            return new ArrayList<String>();
        }

        int length = s.length();
        if (length <= 10) {
            return new ArrayList<String>();
        }
        int end = length - 9;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int cursor = 0; cursor < end; cursor++) {
            String scanning = s.substring(cursor, cursor + 10);
            Integer count = map.get(scanning);
            if (count == null || count == 0) {
                map.put(scanning, 1);
            } else {
                map.put(scanning, ++count);
            }
        }
        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /**
     * 71 Simplify Path
     * <p>
     * https://leetcode.com/problems/simplify-path
     *
     * @param path
     * @return
     */

    public String simplifyPath(String path) {
        String newPath = path.replace("//", "/");
        while (newPath.contains("//")) {
            newPath = newPath.replace("//", "/");
        }
        String[] pathList = newPath.split("/");
        Stack<String> stack = new Stack<String>();
        for (int i = 1; i < pathList.length; i++) {
            if (".".equals(pathList[i])) {

            } else if ("..".equals(pathList[i])) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(pathList[i]);
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            if (!"".equals(result)) {
                result = stack.pop() + "/" + result;
            } else {
                result = stack.pop() + result;
            }
        }
        return "/" + result;
    }

    /**
     * 583. Delete Operation for Two Strings
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String a = "/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///";
        System.out.println(solution.simplifyPath(a));
    }
}
