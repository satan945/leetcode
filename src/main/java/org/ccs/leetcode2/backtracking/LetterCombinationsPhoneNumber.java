/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 17. Letter Combinations of a Phone Number
 * 
 * @author abel created on 2018/1/2 上午9:49
 * @version $Id$
 */
public class LetterCombinationsPhoneNumber {

    public List<String> letterCombinations(String digits) {
        String[] BUTTONS = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> res = new ArrayList<>();
        helper(res, "", 0, digits, BUTTONS);
        return res;

    }

    private void helper(List<String> res, String exist, int index, String digits, String[] buttons) {
        if (exist.length() == digits.length()) {
            res.add(exist);
            return;
        }
        String letters = buttons[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            helper(res, exist + letters.charAt(i), index + 1, digits, buttons);
        }
    }

    private final String[] KEYS = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations2(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        generateCombinations(res, digits, 0, new StringBuilder());
        return res;
    }

    private void generateCombinations(List<String> res, String digits, int pos, StringBuilder sb) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String vals = KEYS[digits.charAt(pos) - '0'];
        for (int i = 0; i < vals.length(); i++) {
            sb.append(vals.charAt(i));
            generateCombinations(res, digits, pos + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinations3(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < digits.length(); i++) {
            int pos = digits.charAt(i) - '0';
            String vals = KEYS[pos];
            if (!queue.isEmpty()) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    String str = queue.poll();
                    for (int k = 0; k < vals.length(); k++) {
                        queue.offer(str + vals.charAt(k));
                    }
                }
            } else {
                for (int j = 0; j < vals.length(); j++) {
                    queue.offer(String.valueOf(vals.charAt(j)));
                }
            }
        }
        while(!queue.isEmpty()){
            res.add(queue.poll());
        }
        return res;
    }

    public static void main(String[] args) {
        LetterCombinationsPhoneNumber solution = new LetterCombinationsPhoneNumber();
        System.out.println(solution.letterCombinations3("234"));
    }

}
