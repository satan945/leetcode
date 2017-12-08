/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.trie.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 677. Map Sum Pairs
 * <p>
 * https://leetcode.com/problems/map-sum-pairs
 * <p>
 * Implement a MapSum class with insert, and sum methods.
 * 
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer
 * represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
 * 
 * For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the
 * pairs' value whose key starts with the prefix.
 * 
 * </p>
 * 
 * @author abel created on 2017/12/7 下午2:50
 * @version $Id$
 */
public class MapSumTrie {

    private TrieWithValue root;

    /** Initialize your data structure here. */
    public MapSumTrie() {
        root = new TrieWithValue();
    }

    public void insert(String key, int val) {
        TrieWithValue node = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (node.links[ch - 'a'] == null) {
                node.links[ch - 'a'] = new TrieWithValue();
            }
            node = node.links[ch - 'a'];
        }
        node.val = val;
    }

    public int sum(String prefix) {
        TrieWithValue node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (node.links[ch - 'a'] == null) {
                return 0;
            }

            node = node.links[ch - 'a'];
        }
        return dfsCalSum(node);
    }

    private int dfsCalSum(TrieWithValue node) {
        if (node == null) {
            return 0;
        }
        int sum = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.links[c - 'a'] != null) {
                sum += dfsCalSum(node.links[c - 'a']);
            }
        }
        return sum + node.val;
    }

    class TrieWithValue {
        public TrieWithValue[] links;
        public boolean isEnd;
        public int val;

        public TrieWithValue() {
            links = new TrieWithValue[26];
        }
    }

    public static void main(String[] args) {
        MapSumTrie mapSumTrie = new MapSumTrie();
        mapSumTrie.insert("aa", 3);
        System.out.println(mapSumTrie.sum("ap"));
        mapSumTrie.insert("aa", 2);
        System.out.println(mapSumTrie.sum("a"));
        System.out.println(mapSumTrie.sum("aa"));
        mapSumTrie.insert("aaa", 3);
        System.out.println(mapSumTrie.sum("aaa"));
        System.out.println(mapSumTrie.sum("bbb"));
    }
}
