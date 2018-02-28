/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.HashMap;
import java.util.Map;

/**
 * 266. Palindrome Permutation
 * 
 * @author abel created on 2018/2/25 下午11:20
 * @version $Id$
 */
public class PalindromePermutation {

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        int oddCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            oddCount += (countMap.get(c) % 2 == 0 ? -1 : 1);
        }
        return oddCount <= 1;
    }

    public static void main(String[] args) {
        PalindromePermutation palindromePermutation = new PalindromePermutation();
        System.out.println(palindromePermutation.canPermutePalindrome("aab"));
    }
}
