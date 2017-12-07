/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.trie.easy;

import java.util.Arrays;

/**
 * @author abel created on 2017/12/5 下午10:26
 * @version $Id$
 */
public class Solution {

    /**
     * 720. Longest Word in Dictionary
     * <p>
     * https://leetcode.com/problems/longest-word-in-dictionary
     * <p>
     * Given a list of strings words representing an English Dictionary, find the longest word in words that can be
     * built one character at a time by other words in words. If there is more than one possible answer, return the
     * longest word with the smallest lexicographical order.
     * 
     * If there is no answer, return the empty string.
     * </p>
     * 
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Trie root = buildTrie(words);
        // Arrays.sort(words, ((o1, o2) -> {
        // if (o1.length() == o2.length()) {
        // return o1.compareTo(o2);
        // } else {
        // return o2.length() - o1.length();
        // }
        // }));
        String res = "";
        for (String word : words) {
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.links[ch - 'a'] == null || !node.links[ch - 'a'].isWord) {
                    break;
                } else {
                    node = node.links[ch - 'a'];
                }
                if (i == word.length() - 1) {
                    if (res.length() == word.length()) {
                        res = res.compareTo(word) < 0 ? res : word;
                    } else if (res.length() < word.length()) {
                        res = word;
                    }
                }
            }
        }
        return res;
    }

    private Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie node = root;
            char[] array = word.toCharArray();
            for (int i = 0; i < array.length; i++) {
                if (node.links[array[i] - 'a'] == null) {
                    node.links[array[i] - 'a'] = new Trie();
                }
                node = node.links[array[i] - 'a'];
            }
            node.isWord = true;
        }
        return root;
    }

    class Trie {
        public Trie[] links;
        public boolean isWord;

        public Trie() {
            links = new Trie[26];
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] { "apple", "ap", "a", "app", "appl", "baddde", "abcedfg", "apply" };
        System.out.println(solution.longestWord(words));
    }
}
