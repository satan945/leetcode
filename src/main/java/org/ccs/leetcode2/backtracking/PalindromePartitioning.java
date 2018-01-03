/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abel created on 2018/1/2 下午10:10
 * @version $Id$
 */
public class PalindromePartitioning {
    /**
     * 131. Palindrome Partitioning
     * <p>
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * 
     * Return all possible palindrome partitioning of s.
     * 
     * For example, given s = "aab"
     * </p>
     * 
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();

        return res;
    }
}
