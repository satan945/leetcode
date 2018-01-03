/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 320. Generalized Abbreviation
 * <p>
 * Write a function to generate the generalized abbreviations of a word.
 * 
 * Example: Given word = "word", return the following list (order does not matter): ["word", "1ord", "w1rd", "wo1d",
 * "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * </p>
 * 
 * @author abel created on 2018/1/3 下午2:08
 * @version $Id$
 */
public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        if (word == null) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        helper(word, "", 0, 0, res);
        return res;
    }

    private void helper(String word, String prefix, int pos, int count, List<String> res) {
        if (pos >= word.length()) {
            if (count != 0) {
                prefix += count;
            }
            res.add(prefix);
            return;
        }
        helper(word, prefix, pos + 1, count + 1, res);
        helper(word, prefix + (count != 0 ? String.valueOf(count) : "") + word.charAt(pos), pos + 1, 0, res);
    }

    public static void main(String[] args) {
        GeneralizedAbbreviation solution = new GeneralizedAbbreviation();
        System.out.println(solution.generateAbbreviations("world"));
    }
}
