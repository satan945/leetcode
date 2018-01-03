/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

/**
 * 211. Add and Search Word - Data structure design
 * <p>
 * Design a data structure that supports the following two operations:
 * 
 * void addWord(word) bool search(word) search(word) can search a literal word or a regular expression string containing
 * only letters a-z or .. A . means it can represent any one letter.
 * 
 * For example:
 * 
 * addWord("bad")
 * 
 * addWord("dad")
 * 
 * addWord("mad")
 * 
 * search("pad") -> false
 * 
 * search("bad") -> true
 * 
 * search(".ad") -> true
 * 
 * search("b..") -> true
 * </p>
 * 
 * @author abel created on 2018/1/3 下午2:19
 * @version $Id$
 */
public class WordDictionary {
    private Trie root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Trie();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Trie node = root;
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (node.links[letters[i] - 'a'] == null) {
                node.links[letters[i] - 'a'] = new Trie();
            }
            node = node.links[letters[i] - 'a'];
        }
        node.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one
     * letter.
     */
    public boolean search(String word) {
        return searchTrie(word, root);
    }

    private boolean searchTrie(String word, Trie node) {
        if (word.length() == 0 && node.isWord) {
            return true;
        }
        if (word.length() == 0) {
            return false;
        }
        char ch = word.charAt(0);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.links[i] == null) {
                    continue;
                }
                if (searchTrie(word.substring(1), node.links[i])) {
                    return true;
                }
            }
        } else {
            if (node.links[ch - 'a'] == null) {
                return false;
            } else {
                return searchTrie(word.substring(1), node.links[ch - 'a']);
            }
        }
        return false;
    }

    class Trie {
        public Trie[] links;
        public boolean isWord;

        public Trie() {
            links = new Trie[26];
            isWord = false;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        wordDictionary.addWord("a");
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("bat"));
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));
    }
}
