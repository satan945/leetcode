/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

/**
 * 79. Word Search
 * <p>
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 * </p>
 * 
 * @author abel created on 2018/1/2 上午10:32
 * @version $Id$
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        char[] keys = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (find(board, i, j, keys, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, int i, int j, char[] keys, int index, boolean[][] visited) {
        if (index == keys.length) {
            return true;
        }
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[i].length - 1 || visited[i][j]) {
            return false;
        }
        if (board[i][j] == keys[index]) {
            visited[i][j] = true;
            if (find(board, i + 1, j, keys, index + 1, visited) //
                    || find(board, i - 1, j, keys, index + 1, visited)//
                    || find(board, i, j + 1, keys, index + 1, visited)//
                    || find(board, i, j - 1, keys, index + 1, visited)) {//
                return true;
            }
            visited[i][j] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        WordSearch solution = new WordSearch();
        char[][] board = new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        System.out.println(solution.exist(board, "ABCB"));
    }
}
