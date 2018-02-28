/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.Arrays;

/**
 * @author abel created on 2018/2/23 下午4:42
 * @version $Id$
 */
public class TicTacToeMatrix {

    private char[][] board;
    private static final char MARK1 = 'X';
    private static final char MARK2 = 'O';
    private static final char EMPTY = ' ';
    private static final int PLAYER1 = 1;
    private static final int PLAYER2 = 2;
    private static final int NONE = 0;

    public TicTacToeMatrix(int n) {
        board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, EMPTY);
        }
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
        if (!checkInput(row, col, player)) {
            return NONE;
        }
        board[row][col] = player == PLAYER1 ? MARK1 : MARK2;
        return checkWin(row, col, player);
    }

    private int checkWin(int row, int col, int player) {
        int n = board.length;
        char mark = player == PLAYER1 ? MARK1 : MARK2;

        for (int i = 0; i < n; i++) {
            if (board[row][i] == mark) {
                if (i == n - 1) {
                    return player;
                }
            } else {
                break;
            }

        }

        for (int i = 0; i < n; i++) {
            if (board[i][col] == mark) {
                if (i == n - 1) {
                    return player;
                }
            } else {
                break;
            }
        }

        if (row == col) {
            for (int i = 0; i < n; i++) {
                if (board[i][i] == mark) {
                    if (i == n - 1) {
                        return player;
                    }
                } else {
                    break;
                }
            }
        }
        if (row + col == n - 1) {
            for (int i = n - 1; i >= 0; i++) {
                if (board[i][n - 1 - i] == mark) {
                    if (i == 0) {
                        return player;
                    }
                } else {
                    break;
                }
            }
        }
        return NONE;
    }

    private boolean checkInput(int row, int col, int player) {
        if (player != PLAYER1 && player != PLAYER2) {
            return false;
        }
        int n = board.length;
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return false;
        }
        if (board[row][col] != EMPTY) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToeMatrix ticTacToeMatrix = new TicTacToeMatrix(3);
        System.out.println(ticTacToeMatrix.move(0, 0, 1));
        System.out.println(ticTacToeMatrix.move(0, 2, 2));

    }

}
