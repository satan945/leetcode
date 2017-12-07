/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.hashtable.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 676. Implement Magic Dictionary
 * <p>
 * https://leetcode.com/problems/implement-magic-dictionary
 * <p>
 * Implement a magic directory with buildDict, and search methods.
 * 
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
 * 
 * For the method search, you'll be given a word, and judge whether if you modify exactly one character into another
 * character in this word, the modified word is in the dictionary you just built.
 * </p>
 * 
 * @author abel created on 2017/12/7 下午12:03
 * @version $Id$
 */
public class MagicDictionary {
    Map<String, List<int[]>> map = new HashMap<>();

    /** Initialize your data structure here. */
    public MagicDictionary() {
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String s : dict) {
            for (int i = 0; i < s.length(); i++) {
                String key = s.substring(0, i) + s.substring(i + 1);
                int[] pair = new int[] { i, s.charAt(i) };

                List<int[]> val = map.getOrDefault(key, new ArrayList<>());
                val.add(pair);

                map.put(key, val);
            }
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for (int i = 0; i < word.length(); i++) {
            String key = word.substring(0, i) + word.substring(i + 1);
            if (map.containsKey(key)) {
                for (int[] pair : map.get(key)) {
                    if (pair[0] == i && pair[1] != word.charAt(i))
                        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] dict = new String[] { "hello", "leetcode" };
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(dict);
        // System.out.println(magicDictionary.search("hello"));
        System.out.println(magicDictionary.search("leetcoded"));

    }
}
