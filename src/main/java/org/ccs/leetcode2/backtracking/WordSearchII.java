/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 212. Word Search II
 * <p>
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * </p>
 * 
 * @author abel created on 2018/2/5 下午4:00
 * @version $Id$
 */
public class WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        Trie root = buildTrie(words);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                searchTrie(board, i, j, root, new boolean[board.length][board[i].length], res);
            }
        }
        return res;
    }

    private void searchTrie(char[][] board, int i, int j, Trie node, boolean[][] visited, List<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || visited[i][j]) {
            return;
        }
        char ch = board[i][j];
        if (node.links[ch - 'a'] == null) {
            return;
        }
        node = node.links[ch - 'a'];
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        visited[i][j] = true;
        int[] moves = new int[] { 1, 0, -1, 0, 1 };
        for (int k = 0; k < moves.length - 1; k++) {
            searchTrie(board, i + moves[k], j + moves[k + 1], node, visited, res);
        }
        visited[i][j] = false;
    }

    private Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.links[ch - 'a'] == null) {
                    node.links[ch - 'a'] = new Trie();
                }
                node = node.links[ch - 'a'];
            }
            node.isWord = true;
            node.word = word;
        }
        return root;
    }

    class Trie {
        public Trie[] links;
        public boolean isWord;
        public String word;

        public Trie() {
            this.links = new Trie[26];
        }
    }

    public static void main(String[] args) {
        String[] words = new String[] { "oath", "pea", "eat", "rain" };
        char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' } };
        WordSearchII solution = new WordSearchII();
        System.out.println(solution.findWords(board, words));
    }
}
