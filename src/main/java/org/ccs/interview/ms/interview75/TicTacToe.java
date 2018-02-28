/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.Arrays;

/**
 * @author abel created on 2018/2/23 下午5:13
 * @version $Id$
 */
public class TicTacToe {
    private int[] cols;
    private int[] rows;
    private int n;
    private int diagnol;
    private int antidaignol;

    public TicTacToe(int n) {
        cols = new int[n];
        rows = new int[n];
        diagnol = 0;
        antidaignol = 0;
        this.n = n;
    }

    public int move(int row, int col, int player) {
        int add = player == 1 ? 1 : -1;
        rows[row] += add;
        cols[col] += add;
        if (row == col) {
            diagnol+=add;
        }
        if (row + col == n - 1) {
            antidaignol+=add;
        }
        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diagnol) == n
                || Math.abs(antidaignol) == n) {
            return player;
        }
        return 0;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToeMatrix = new TicTacToe(3);
        System.out.println(ticTacToeMatrix.move(0, 0, 1));
        System.out.println(ticTacToeMatrix.move(0, 2, 2));

    }
}
