/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.LinkedList;

/**
 * @author abel created on 2017/11/29 下午7:07
 * @version $Id$
 */
public class SnakeGame {
    private int length;
    private int height, width;
    private LinkedList<int[]> snake;
    int[][] food;
    int foodIndex;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        foodIndex = 0;
        length = 0;
        snake = new LinkedList<>();
        snake.offer(new int[] { 0, 0 });
    }

    public int move(String direction) {
        int[] curPos = new int[] { snake.get(0)[0], snake.get(0)[1] };
        switch (direction) {
        case "U":
            curPos[0]--;
            break;
        case "D":
            curPos[0]++;
            break;
        case "L":
            curPos[1]--;
            break;
        case "R":
            curPos[1]++;
            break;
        default:
            break;
        }
        if (curPos[0] < 0 || curPos[0] >= height || curPos[1] < 0 || curPos[1] >= width) {
            return -1;
        }

        for (int i = 1; i < snake.size() - 1; i++) {
            int[] pos = snake.get(i);
            if (pos[0] == curPos[0] && pos[1] == curPos[1]) {
                return -1;
            }
        }
        snake.addFirst(curPos);
        if (length < food.length) {
            int[] nextFood = food[length];
            if (curPos[0] == nextFood[0] && curPos[1] == nextFood[1]) {
                length++;
            }
        }
        while (snake.size() > length + 1) {
            snake.removeLast();
        }
        return length;

    }

}
