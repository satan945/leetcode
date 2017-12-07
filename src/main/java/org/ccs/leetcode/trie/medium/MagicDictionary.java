/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.trie.medium;

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
    private Trie root;

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {
    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        root = new Trie();
        for (String word : dict) {
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.links[ch - 'a'] == null) {
                    node.links[ch - 'a'] = new Trie();
                }
                node = node.links[ch - 'a'];
            }
            node.isWord = true;
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (char ch = 'a'; ch < 'z'; ch++) {
                if (ch == chars[i]) {
                    continue;
                }
                char origin = chars[i];
                chars[i] = ch;
                if (find(chars)) {
                    return true;
                }
                chars[i] = origin;
            }
        }
        return false;
    }

    private boolean find(char[] chars) {
        Trie node = root;
        for (char ch : chars) {
            if (node.links[ch - 'a'] != null) {
                node = node.links[ch - 'a'];
            }else{
                return false;
            }
        }
        return node.isWord;
    }

    class Trie {
        public Trie[] links;
        public boolean isWord;

        public Trie() {
            links = new Trie[26];
        }
    }

    public static void main(String[] args) {
        String[] dict = new String[] { "hello", "leetcode" };
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(dict);
        // System.out.println(magicDictionary.search("hello"));
        System.out.println(magicDictionary.search("leetcoded"));

    }
}
