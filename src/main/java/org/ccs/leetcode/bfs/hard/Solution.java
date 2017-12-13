/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.bfs.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author abel created on 2017/12/12 下午5:21
 * @version $Id$
 */
public class Solution {
    /**
     *
     * 
     * 301. Remove Invalid Parentheses
     * <p>
     * https://leetcode.com/problems/remove-invalid-parentheses
     * <p>
     * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
     * results.
     * 
     * Note: The input string may contain letters other than the parentheses ( and ).
     * </p>
     * 
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;
        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                found = true;
                res.add(s);
            }
            if (found) {
                continue;
            }
            for (int i = 0; i < s.length(); i++) {
                System.out.println(queue);
                char ch = s.charAt(i);
                if (ch != '(' && ch != ')') {
                    continue;
                }
                String t = s.substring(0, i) + s.substring(i + 1);
                if (!visited.contains(t)) {
                    visited.add(t);
                    queue.offer(t);
                }
            }
        }
        return res;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                count++;
            }
            if (ch == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String a = "()))";
        System.out.println(solution.removeInvalidParentheses(a));
    }
}
