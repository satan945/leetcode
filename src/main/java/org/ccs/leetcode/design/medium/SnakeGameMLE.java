/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 353. Design Snake Game
 * <p>
 * https://leetcode.com/problems/design-snake-game
 * <p>
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not
 * familiar with the game.
 * 
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * 
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's
 * score both increase by 1.
 * 
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was
 * eaten by the snake.
 * 
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 * </p>
 * 
 * @author abel created on 2017/11/29 下午6:07
 * @version $Id$
 */
public class SnakeGameMLE {
    private char[][] board;
    private int[] loc;
    private Deque<int[]> queue;
    private Deque<int[]> foodQueue = null;
    private int length;
    private int width;
    private int height;

    /**
     * Initialize your data structure here.
     * 
     * @param width - screen width
     * @param height - screen height
     * @param food - A list of food positions E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the
     *            second is at [1,0].
     */
    public SnakeGameMLE(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        board = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(board[i], 'E');
        }
        if (food != null && food.length != 0) {
            foodQueue = new LinkedList<>();
            for (int[] f : food) {
                foodQueue.offer(f);
            }
            int[] f = foodQueue.poll();
            board[f[0]][f[1]] = 'F';
        }
        board[0][0] = 'S';
        loc = new int[] { 0, 0 };
        length = 0;
        queue = new LinkedList<>();
        queue.offer(loc);
    }

    /**
     * Moves the snake.
     * 
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over. Game over when snake crosses the screen boundary
     *         or bites its body.
     */
    public int move(String direction) {
        int[] newLoc = tryMove(direction);
        if (!validLoc(newLoc)) {
            return -1;
        }
        if (board[newLoc[0]][newLoc[1]] == 'F') {
            length++;
            if (foodQueue != null && !foodQueue.isEmpty()) {
                int[] f = foodQueue.poll();
                board[f[0]][f[1]] = 'F';
            }
        } else {
            int[] tail = queue.removeFirst();
            board[tail[0]][tail[1]] = 'E';
        }
        if (board[newLoc[0]][newLoc[1]] == 'S') {
            return -1;
        }
        queue.offer(newLoc);
        loc = newLoc;
        board[newLoc[0]][newLoc[1]] = 'S';
        return length;
    }

    private boolean validLoc(int[] newLoc) {
        if (newLoc[0] < 0 || newLoc[0] >= height || newLoc[1] < 0 || newLoc[1] >= width) {
            return false;
        }

        return true;
    }

    private int[] tryMove(String direction) {
        int x = -1;
        int y = -1;
        switch (direction) {
        case "U":
            x = loc[1];
            y = loc[0] - 1;
            break;
        case "D":
            x = loc[1];
            y = loc[0] + 1;
            break;
        case "L":
            x = loc[1] - 1;
            y = loc[0];
            break;
        case "R":
            x = loc[1] + 1;
            y = loc[0];
            break;
        default:
            break;
        }
        System.out.println("Direction=" + direction + ",x=" + x + "，y=" + y);
        return new int[] { y, x };
    }

    public static void main(String[] args) {
        SnakeGameMLE snakeGame = new SnakeGameMLE(3, 3, new int[][] { { 2, 0 }, { 0, 0 }, { 0, 2 }, { 2, 2 } });
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("D"));
    }
}
