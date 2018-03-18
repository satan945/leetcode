/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

/**
 * @author abel created on 2018/3/12 下午3:58
 * @version $Id$
 */
public class WordDictionary2 {
    private Trie root;

    /** Initialize your data structure here. */
    public WordDictionary2() {
        root = new Trie();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
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

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one
     * letter.
     */
    public boolean search(String word) {
        Trie node = root;
        char[] chars = word.toCharArray();
        return searchInTrie(chars, 0, node);
    }

    public boolean searchInTrie(char[] chars, int pos, Trie node) {
        if (pos == chars.length && node.isWord) {
            return true;
        }
        char ch = chars[pos];
        if (ch != '.') {
            if (node.links[ch - 'a'] == null) {
                return false;
            }
            return searchInTrie(chars, pos + 1, node.links[ch - 'a']);
        } else {
            for (int i = 0; i < 26; i++) {
                if (node.links[i] != null && searchInTrie(chars, pos + 1, node.links[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    class Trie {
        public Trie[] links;
        public boolean isWord;

        public Trie() {
            links = new Trie[26];
        }
    }

    public static void main(String[] args) {
        WordDictionary2 dictionary2 = new WordDictionary2();
        dictionary2.addWord("at");
        dictionary2.addWord("and");
        dictionary2.addWord("add");
        dictionary2.addWord("an");
        dictionary2.addWord("a");
        System.out.println(dictionary2.search(".at"));
        System.out.println(dictionary2.search("a"));
    }
}
