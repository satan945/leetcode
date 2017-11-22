/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import org.ccs.leetcode.trie.medium.TrieNode;

/**
 * 211. Add and Search Word - Data structure design
 * <p>
 * https://leetcode.com/problems/add-and-search-word-data-structure-design
 * <p>
 * Design a data structure that supports the following two operations:
 * 
 * void addWord(word) bool search(word) search(word) can search a literal word or a regular expression string containing
 * only letters a-z or .. A . means it can represent any one letter.
 * 
 * For example:
 * 
 * addWord("bad") addWord("dad") addWord("mad") search("pad") -> false search("bad") -> true search(".ad") -> true
 * search("b..") -> true Note: You may assume that all words are consist of lowercase letters a-z.
 * </p>
 * 
 * @author Abel created on 2017/11/3 11:23
 * @version $Id$
 */
public class WordDictionary {
    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one
     * letter.
     */
    public boolean search(String word) {
        return search(word, root);
    }

    public boolean search(String rest, TrieNode root) {
        if (rest.length() == 0) {
            if (root.isEnd()) {
                return true;
            } else {
                return false;
            }
        }

        char ch = rest.charAt(0);
        if (ch != '.') {
            if (!root.containsKey(ch)) {
                return false;
            } else {
                return search(rest.substring(1), root.get(ch));
            }
        } else {
            TrieNode[] links = root.getLinks();
            for (int j = 0; j < root.getR(); j++) {
                if (links[j] != null) {
                    if (search(rest.substring(1), root.get((char) ('a' + j)))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search("bat"));
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));

    }

}
