/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.trie.medium;

/**
 * 208. Implement Trie (Prefix Tree)
 * <p>
 * https://leetcode.com/problems/implement-trie-prefix-tree
 *
 * <p>
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 * </p>
 * https://leetcode.com/problems/implement-trie-prefix-tree/solution/
 * 
 * @author abel created on 2017/11/2 下午9:40
 * @version $Id$
 */
public class Trie {
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
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

    public TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char pre = prefix.charAt(i);
            if (node.containsKey(pre)) {
                node = node.get(pre);
            } else {
                return null;
            }
        }
        return node;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    public TrieNode getRoot() {
        return root;
    }

}
