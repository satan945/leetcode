/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 288. Unique Word Abbreviation
 * <p>
 * https://leetcode.com/problems/unique-word-abbreviation
 * <p>
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word
 * abbreviations:
 * 
 * a) it --> it (no abbreviation)
 * 
 * 1 b) d|o|g --> d1g
 * 
 * 1 1 1 1---5----0----5--8 c) i|nternationalizatio|n --> i18n
 * 
 * 1 1---5----0 d) l|ocalizatio|n --> l10n Assume you have a dictionary and given a word, find whether its abbreviation
 * is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same
 * abbreviation.
 * 
 * Example: Given dictionary = [ "deer", "door", "cake", "card" ]
 * 
 * isUnique("dear") -> false
 * 
 * isUnique("cart") -> true
 * 
 * isUnique("cane") -> false
 * 
 * isUnique("make") -> true
 * </p>
 * 
 * @author abel created on 2017/11/29 下午5:19
 * @version $Id$
 */
public class ValidWordAbbr {
    private Map<String, Integer> dictMap;
    private Set<String> set;

    public ValidWordAbbr(String[] dictionary) {
        dictMap = new HashMap<>();
        set = new HashSet<>();
        for (String word : dictionary) {
            if (set.add(word)) {
                String key = abbrWord(word);
                dictMap.put(key, dictMap.getOrDefault(key, 0) + 1);
            }
        }
    }

    private String abbrWord(String word) {
        if (word.length() <= 2) {
            return word;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0)).append(word.length() - 2).append(word.charAt(word.length() - 1));
        return sb.toString();
    }

    public boolean isUnique(String word) {
        String key = abbrWord(word);
        if (!dictMap.containsKey(key)) {
            return true;
        }
        int count = dictMap.get(key);
        if (set.contains(word)) {
            return count == 1;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String[] dic = new String[] { "hello" };
        ValidWordAbbr validWordAbbr = new ValidWordAbbr(dic);
        System.out.println(validWordAbbr.isUnique("hello"));
    }
}
