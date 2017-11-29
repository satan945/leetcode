/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.trie.hard;

import org.ccs.leetcode.trie.medium.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abel created on 2017/11/26 下午11:06
 * @version $Id$
 */
public class Solution {
    /**
     * 212. Word Search II
     * <p>
     * https://leetcode.com/problems/word-search-ii
     * <p>
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     * 
     * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
     * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     * </p>
     * 
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfsSearch(board, i, j, root, res, visited);
            }
        }
        return res;
    }

    private void dfsSearch(char[][] board, int i, int j, TrieNode node, List<String> res, boolean[][] visited) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) {
            return;
        }
        char c = board[i][j];
        if (visited[i][j] || !node.containsKey(c)) {
            return;
        }
        node = node.get(c);
        if (node.getWord() != null) {
            res.add(node.getWord());
            node.setWord(null);
        }
        visited[i][j] = true;
        int[] move = new int[] { 1, 0, -1, 0, 1 };
        for (int m = 0; m < move.length - 1; m++) {
            dfsSearch(board, i + move[m], j + move[m + 1], node, res, visited);
        }
        visited[i][j] = false;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (!node.containsKey(chars[i])) {
                    node.put(chars[i], new TrieNode());
                }
                node = node.get(chars[i]);
            }
            node.setEnd();
            node.setWord(word);
        }
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = { { 'a', 'b' }, { 'c', 'd' } };
        String[] words = new String[] { "ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb" };
        System.out.println(solution.findWords(board, words));
    }
}
