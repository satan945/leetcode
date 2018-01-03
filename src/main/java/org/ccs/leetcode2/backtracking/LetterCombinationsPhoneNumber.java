/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        LetterCombinationsPhoneNumber solution = new LetterCombinationsPhoneNumber();
        System.out.println(solution.letterCombinations("234"));
    }

}
