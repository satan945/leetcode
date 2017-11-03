/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

/**
 * @author abel created on 2017/11/2 下午10:27
 * @version $Id$
 */
public class TrieNode {
    private TrieNode[] links;
    private final int R = 26;
    private boolean isEnd = false;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public int getR() {
        return R;
    }

    public TrieNode[] getLinks() {
        return links;
    }
}
