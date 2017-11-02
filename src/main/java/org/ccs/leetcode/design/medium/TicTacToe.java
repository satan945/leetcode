/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.Arrays;

/**
 * @author abel created on 2017/11/1 下午5:35
 * @version $Id$
 */
public class TicTacToe {
    private int winner = 0;
    private char[][] board;
    private int n;
    private static char Player1Mark = 'X';
    private static char Player2Mark = 'O';

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, 'E');
        }
        this.n = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * 
     * @param row The row of the board.
     * @param col The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        if (board[row][col] != 'E') {
            return winner;
        }
        board[row][col] = player == 1 ? Player1Mark : Player2Mark;
        return judgeWin(player, board, row, col);
    }

    private int judgeWin(int player, char[][] board, int row, int col) {
        char mark = player == 1 ? Player1Mark : Player2Mark;
        if (row == col) {
            // 对角线
            for (int i = 0, j = 0; i < n; i++, j++) {
                if (board[i][j] != mark) {
                    break;
                } else if (board[i][j] == mark && i == n - 1 && j == n - 1) {
                    winner = player;
                    return winner;
                }
            }
        }
        if (row + col == n) {
            for (int i = n, j = 0; j < n; i++, j--) {
                if (board[i][j] != mark) {
                    break;
                } else if (board[i][j] == mark && i == n - 1 && j == n - 1) {
                    winner = player;
                    return winner;
                }
            }
        }
        // 行
        for (int i = 0; i < n; i++) {
            if (board[i][col] != mark) {
                break;
            } else if (i == n - 1) {
                winner = player;
                return winner;
            }
        }
        // 列
        for (int j = 0; j < n; j++) {
            if (board[row][j] != mark) {
                break;
            } else if (j == n - 1) {
                winner = player;
                return winner;
            }
        }
        return winner;
    }
}
